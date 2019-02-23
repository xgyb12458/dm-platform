package com.damon.product.domain.spu.saga;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
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


    /*
    @StartSaga
    @EventHandler
    private void on(SpuCreatedEvent event) {
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
    */
}
