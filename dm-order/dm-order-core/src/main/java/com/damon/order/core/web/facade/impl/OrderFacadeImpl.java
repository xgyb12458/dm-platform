package com.damon.order.core.web.facade.impl;

import com.damon.order.api.dto.req.trade.ConfirmOrderReqDTO;
import com.damon.order.api.dto.req.trade.QueryOrdersReqDTO;
import com.damon.order.api.dto.req.trade.SubmitOrderReqDTO;
import com.damon.order.api.dto.req.trade.UpdateOrderReqDTO;
import com.damon.order.api.dto.resp.trade.ConfirmOrderRespDTO;
import com.damon.order.api.dto.resp.trade.OrderInfoRespDTO;
import com.damon.order.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.order.api.web.facade.OrderFacade;
import com.damon.order.domain.trade.aggregate.TradeId;
import com.damon.order.domain.trade.command.*;
import com.damon.order.domain.user.aggregate.*;
import com.damon.shared.common.Constants;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 订单管理接口
 * @author Damon S.
 */
@Api(tags = "订单管理接口")
@RequiredArgsConstructor
@RestController
public class OrderFacadeImpl implements OrderFacade {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;


    @ArgsValid @Override
    @ApiOperation(value = "查询订单", notes = "根据指定条件查询订单列表")
    public ResponseWrapper<OrderInfoRespDTO> list(QueryOrdersReqDTO queryOrdersReqDTO) {
        Long currentUserId = 100000L;
        // 非此用户的订单，不可查看；管理员除外
        QueryOrderCommand command = QueryOrderCommand.builder()
                .payState(queryOrdersReqDTO.getPayState())
                .orderState(queryOrdersReqDTO.getOrderState())
                .pageIndex(Optional.ofNullable(queryOrdersReqDTO.getPageIndex())
                        .orElse(Constants.START_PAGE_INDEX))
                .pageSize(Optional.ofNullable(queryOrdersReqDTO.getPageSize())
                        .orElse(Constants.DEFAULT_PAGE_SIZE))
                .build();

        queryGateway.query(command, List.class);

        return null;
    }

    @ArgsValid @Override
    @ApiOperation(value = "更新订单", notes = "更新订单信息")
    public ResponseWrapper<Boolean> update(UpdateOrderReqDTO updateOrderReqDTO) {
        Long currentUserId = 100000L;
        // 非此用户的订单，不可更新；管理员除外
        UpdateOrderCommand command = UpdateOrderCommand.builder()
                .build();

        commandGateway.send(command);

        return null;
    }

    @ArgsValid @Override
    @ApiOperation(value = "取消订单", notes = "取消订单")
    public ResponseWrapper<OrderInfoRespDTO> cancel(Long orderId) {
        return null;
    }

    @ArgsValid @Override
    @ApiOperation(value = "支付订单", notes = "支付订单")
    public ResponseWrapper<OrderInfoRespDTO> pay(Long orderId) {
        return null;
    }

    @ArgsValid @Override
    @ApiOperation(value = "移除订单", notes = "逻辑移除订单")
    public ResponseWrapper<OrderInfoRespDTO> remove(Long orderId) {
        return null;
    }

    @ArgsValid @Override
    @ApiOperation(value = "根据订单ID查找订单", notes = "根据订单ID查找订单")
    public ResponseWrapper<OrderInfoRespDTO> find(Long orderId) {
        Long currentUserId = 100000L;
        // 非此用户的订单，不可查看；管理员除外
        QueryOrderByIdCommand command = QueryOrderByIdCommand.builder()
                .build();

        queryGateway.query(command, OrderInfoRespDTO.class);

        return null;
    }

    @ArgsValid @Override
    @ApiOperation(value = "确认订单", notes = "确认订单参数")
    public ResponseWrapper<ConfirmOrderRespDTO> confirm(
                    ConfirmOrderReqDTO confirmOrderReqDTO) {
        ConfirmOrderCommand command = ConfirmOrderCommand.builder()
                .skuId(new SkuId(confirmOrderReqDTO.getSkuid()))
                .quantity(confirmOrderReqDTO.getQty())
                .cartItemIds(confirmOrderReqDTO.getCid().stream()
                        .map(CartItemId::new)
                        .collect(Collectors.toList())
                ).build();

        queryGateway.query(command, ConfirmOrderRespDTO.class);

        ConfirmOrderRespDTO confirmOrderRespDTO = ConfirmOrderRespDTO.builder().build();

        return new ResponseWrapper<>(confirmOrderRespDTO);
    }


    @ArgsValid @Override
    @ApiOperation(value = "提交订单", notes = "提交商品购买订单")
    public ResponseWrapper<SubmitOrderRespDTO> submit(
            @ApiParam(name = "orderSubmit", value = "订单提交项", required = true)
                    SubmitOrderReqDTO submitOrderReqDTO) {
        Long currentUserId = 0L;
        SubmitOrderCommand command = SubmitOrderCommand.builder()
                .tradeId(new TradeId())
                .addressId(new AddressId(submitOrderReqDTO.getAddressId()))
                .message(submitOrderReqDTO.getMessage())
                .couponIds(submitOrderReqDTO.getCouponIds().stream()
                        .map(CouponId::new)
                        .collect(Collectors.toList())
                )
                .invoiceId(new InvoiceId(submitOrderReqDTO.getInvoiceId()))
                .integration(submitOrderReqDTO.getIntegration())
                .commission(submitOrderReqDTO.getCommission())
                .skus(submitOrderReqDTO.getSkus().stream()
                        .map(sku -> {
                            OrderSku.OrderSkuBuilder builder = OrderSku.builder();
                            if (sku.getCid() > Constants.LONG_ZERO) {
                                builder.cartItemId(new CartItemId(sku.getCid()));
                            } else {
                                builder.skuId(new SkuId(sku.getSkuid())).quantity(sku.getQty());
                            }
                            return builder.build();
                        }).collect(Collectors.toList())
                )
                .payChannel(submitOrderReqDTO.getPayChannel())
                .createdBy(new UserId(currentUserId))
                .build();

        commandGateway.sendAndWait(command);

        SubmitOrderRespDTO submitOrderRespDTO = SubmitOrderRespDTO.builder().build();

        return new ResponseWrapper<>(submitOrderRespDTO);
    }
}
