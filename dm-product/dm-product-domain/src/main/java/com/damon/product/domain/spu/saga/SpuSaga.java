package com.damon.product.domain.spu.saga;

import com.damon.product.domain.sku.aggregate.SkuId;
import com.damon.product.domain.sku.command.CreateSkuCommand;
import com.damon.product.domain.spu.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;


/**
 * SPU SAGA
 * @author Damon S.
 */
@Component
@RequiredArgsConstructor
public class SpuSaga {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    @StartSaga
    @EventHandler
    private void on(ProductCreatedEvent event) {
        event.getSkus().forEach(
                sku -> commandGateway.send(
                        CreateSkuCommand.builder()
                                .skuId(new SkuId())
                                .spuId(event.getSpuId())
                                .specIds(sku.getSpecIds())
                                .skuCode(sku.getSkuCode())
                                .name(sku.getName())
                                .images(sku.getImages())
                                .inventory(sku.getInventory())
                                .secureInventory(sku.getSecureInventory())
                                .price(sku.getPrice())
                                .reduction(sku.getReduction())
                                .promoteFee(sku.getPromoteFee())
                                .serviceFee(sku.getServiceFee())
                                .exchangePrice(sku.getExchangePrice())
                                .exchangePoint(sku.getExchangePoint())
                                .netWorth(sku.getNetWorth())
                                .barCode(sku.getBarCode())
                                .createdBy(event.getCreatedBy())
                                .build())
        );
    }
}
