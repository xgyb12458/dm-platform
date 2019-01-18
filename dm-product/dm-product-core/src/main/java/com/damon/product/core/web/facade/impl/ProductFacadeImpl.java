package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.spu.CreateProductReqDTO;
import com.damon.product.api.dto.req.spu.SubmitOrderReqDTO;
import com.damon.product.api.dto.resp.spu.CreateProductRespDTO;
import com.damon.product.api.dto.resp.trade.SubmitOrderRespDTO;
import com.damon.product.api.web.facade.ProductFacade;
import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.domain.spu.command.CreateProductCommand;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单管理接口
 * @author Damon S.
 */
@Api(tags = "订单管理接口")
@RestController
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    @ArgsValid
    @Override
    @ApiOperation(value = "确认订单", notes = "确认订单参数")
    public ResponseWrapper<CreateProductRespDTO> create(
            CreateProductReqDTO createProductReqDTO) {
        CreateProductCommand command = new CreateProductCommand();
        BeanUtils.copyProperties(createProductReqDTO, command);

        commandGateway.sendAndWait(command);
        CreateProductRespDTO createProductRespDTO = new CreateProductRespDTO();

        return new ResponseWrapper<>(createProductRespDTO);
    }

    @Override
    public ResponseWrapper<SubmitOrderRespDTO> submit(
            SubmitOrderReqDTO submitOrderReqDTO) {
        return null;
    }


//    @ArgsValid @Override
//    @ApiOperation(value = "确认订单", notes = "确认订单参数")
//    public ResponseWrapper<ConfirmOrderRespDTO> confirm(
//                    CreateProductReqDTO createProductReqDTO) {
//        ConfirmOrderCommand command = ConfirmOrderCommand.builder()
//                .skuId(new SkuId(createProductReqDTO.getSkuid()))
//                .quantity(createProductReqDTO.getQty())
//                .promotionId(createProductReqDTO.getPid())
//                .detailId(createProductReqDTO.getDid())
//                .cartItemIds(createProductReqDTO.getCids().stream()
//                        .map(CartItemId::new)
//                        .collect(Collectors.toList())
//                ).build();
//
//        queryGateway.query(command, ConfirmOrderRespDTO.class);
//
//        ConfirmOrderRespDTO confirmOrderRespDTO = ConfirmOrderRespDTO.builder().build();
//
//        return new ResponseWrapper<>(confirmOrderRespDTO);
//    }
//
//
//    @ArgsValid @Override
//    @ApiOperation(value = "提交订单", notes = "提交商品购买订单")
//    public ResponseWrapper<SubmitOrderRespDTO> submit(
//            @ApiParam(name = "orderSubmit", value = "订单提交项", required = true)
//                    SubmitOrderReqDTO submitOrderReqDTO) {
//        Long currentUserId = 0L;
//        SubmitOrderCommand command = SubmitOrderCommand.builder()
//                .tradeId(new TradeId())
//                .addressId(new AddressId(submitOrderReqDTO.getAddressId()))
//                .message(submitOrderReqDTO.getMessage())
//                .couponIds(submitOrderReqDTO.getCouponIds().stream()
//                        .map(CouponId::new)
//                        .collect(Collectors.toList())
//                )
//                .invoiceId(new InvoiceId(submitOrderReqDTO.getInvoiceId()))
//                .integration(submitOrderReqDTO.getIntegration())
//                .commission(submitOrderReqDTO.getCommission())
//                .skus(submitOrderReqDTO.getSkus().stream()
//                        .map(sku -> {
//                            OrderSku.OrderSkuBuilder builder = OrderSku.builder();
//                            if (sku.getCid() > Constants.MAGIC_NUM_0L) {
//                                builder.cartItemId(new CartItemId(sku.getCid()));
//                            } else {
//                                builder.skuId(new SkuId(sku.getSkuid()))
//                                        .quantity(sku.getQty())
//                                        .promotionId(sku.getPid())
//                                        .detailId(sku.getDid());
//                            }
//                            return builder.build();
//                        }).collect(Collectors.toList())
//                )
//                .payChannel(submitOrderReqDTO.getPayChannel())
//                .createdBy(new UserId(currentUserId))
//                .build();
//
//        commandGateway.sendAndWait(command);
//
//        SubmitOrderRespDTO submitOrderRespDTO = SubmitOrderRespDTO.builder().build();
//
//        return new ResponseWrapper<>(submitOrderRespDTO);
//    }
}
