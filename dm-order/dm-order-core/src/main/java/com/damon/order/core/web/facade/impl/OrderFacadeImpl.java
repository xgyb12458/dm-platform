package com.damon.order.core.web.facade.impl;

import com.damon.order.api.dto.req.trade.ConfirmOrderReqDTO;
import com.damon.order.api.dto.req.trade.SubmitOrderReqDTO;
import com.damon.order.api.dto.resp.trade.ConfirmOrderRespDTO;
import com.damon.order.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.order.api.web.facade.OrderFacade;
import com.damon.order.domain.trade.aggregate.TradeId;
import com.damon.order.domain.trade.command.ConfirmOrderCommand;
import com.damon.order.domain.trade.command.SubmitOrderCommand;
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
    @ApiOperation(value = "确认订单", notes = "确认订单参数")
    public ResponseWrapper<ConfirmOrderRespDTO> confirm(
                    ConfirmOrderReqDTO confirmOrderReqDTO) {
        ConfirmOrderCommand command = ConfirmOrderCommand.builder()
                .skuId(new SkuId(confirmOrderReqDTO.getSkuid()))
                .quantity(confirmOrderReqDTO.getQty())
//                .promotionId(confirmOrderReqDTO.getPid())
//                .detailId(confirmOrderReqDTO.getDid())
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
                                builder.skuId(new SkuId(sku.getSkuid()))
                                        .quantity(sku.getQty());
//                                        .promotionId(sku.getPid())
//                                        .detailId(sku.getDid());
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