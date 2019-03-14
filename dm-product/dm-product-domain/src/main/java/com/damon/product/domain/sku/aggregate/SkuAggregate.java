package com.damon.product.domain.sku.aggregate;

import com.damon.product.domain.sku.command.CreateSkuCommand;
import com.damon.product.domain.sku.event.SkuCreatedEvent;
import com.damon.shared.common.Constants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * SKU单品
 * @author Damon S.
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class SkuAggregate {
    @AggregateIdentifier
    private SkuId       skuId;
    private String      skuCode;
    private Long        spuId;
    private List<Long>  specIds;
    private List<Long>  imageIds;
    private Integer     inventory;
    private Integer     safetyStock;
    private Long        price;
    private Long        reduction;
    private Long        promoteFee;
    private Long        serviceFee;
    private Long        exchangePoint;
    private Long        netWorth;
    private String      barCode;
    private Long        createdBy;
    private Long        updatedBy;
    private Instant     createdAt;
    private Instant     updatedAt;


    @CommandHandler
    public SkuAggregate(CreateSkuCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>creating sku aggregate command, parameters: {}", command.toString());

        apply(SkuCreatedEvent.builder()
                .skuId(new SkuId())
                .skuCode(command.getSkuCode())
                .spuId(command.getSpuId())
                .specIds(command.getSpecIds())
                .imageIds(command.getImageIds())
                .inventory(command.getInventory())
                .safetyStock(command.getSafetyStock())
                .price(command.getPrice())
                .reduction(command.getReduction())
                .promoteFee(command.getPromoteFee())
                .serviceFee(command.getServiceFee())
                .exchangePoint(command.getExchangePoint())
                .netWorth(command.getNetWorth())
                .barCode(command.getBarCode())
                .createdBy(command.getCreatedBy())
                .createdAt(Instant.now())
                .build()
        );
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    public void on(SkuCreatedEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>creating sku aggregate event, parameters: {}", event.toString());

        setSkuId(event.getSkuId());
        setSkuCode(event.getSkuCode());
        setSpuId(event.getSpuId());
        setSpecIds(event.getSpecIds());
        setImageIds(event.getImageIds());
        setInventory(event.getInventory());
        setSafetyStock(event.getSafetyStock());
        setPrice(event.getPrice());
        setReduction(event.getReduction());
        setPromoteFee(event.getPromoteFee());
        setServiceFee(event.getServiceFee());
        setExchangePoint(event.getExchangePoint());
        setNetWorth(event.getNetWorth());
        setBarCode(event.getBarCode());
        setCreatedBy(event.getCreatedBy());
        setCreatedAt(event.getCreatedAt());
    }
}
