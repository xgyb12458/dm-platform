package com.damon.product.domain.sku.event;

import com.damon.product.domain.sku.aggregate.SkuId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

/***
 * SKU单品更新成功
 * @author Damon S.
 */
@Value
@Builder
public class SkuUpdatedEvent {
    @TargetAggregateIdentifier
    private final SkuId       skuId;
    @NotNull
    private final Long        spuId;
    private final List<Long> specIds;
    private final String      name;
    private final List<Long>  imageIds;
    private final Integer     inventory;
    private final Integer     safetyStock;
    private final Long        price;
    private final Long        reduction;
    private final Long        promoteFee;
    private final Long        serviceFee;
    private final Long        exchangePrice;
    private final Long        exchangePoint;
    private final Long        netWorth;
    private final String      barCode;
    private final Long        updatedBy;
    private final Instant     updatedAt;
}
