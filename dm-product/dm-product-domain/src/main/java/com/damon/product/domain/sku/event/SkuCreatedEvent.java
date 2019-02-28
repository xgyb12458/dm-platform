package com.damon.product.domain.sku.event;

import com.damon.product.domain.sku.aggregate.SkuId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

/***
 * SKU单品创建成功
 * @author Damon S.
 */
@Value
@Builder
public class SkuCreatedEvent {
    @TargetAggregateIdentifier
    private final SkuId       skuId;
    private final String      name;
    @NotNull
    private final Long        spuId;
    private final List<Long> specIds;
    @NotNull
    private final String      skuCode;
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
    private final Long        createdBy;
    private final Instant     createdAt;
}
