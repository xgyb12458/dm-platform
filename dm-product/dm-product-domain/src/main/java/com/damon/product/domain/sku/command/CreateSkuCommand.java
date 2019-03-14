package com.damon.product.domain.sku.command;

import com.damon.product.domain.sku.aggregate.SkuId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 创建SKU命令
 * @author Damon S.
 */
@Value
@Builder
public class CreateSkuCommand {
    @TargetAggregateIdentifier
    private final SkuId       skuId;
    @NotNull
    private final Long        spuId;
    private final List<Long>  specIds;
    @NotNull
    private final String      skuCode;
    private final List<Long>  imageIds;
    private final Integer     inventory;
    private final Integer     safetyStock;
    private final Long        price;
    private final Long        reduction;
    private final Long        promoteFee;
    private final Long        serviceFee;
    private final Long        exchangePoint;
    private final Long        netWorth;
    private final String      barCode;
    private final Long        createdBy;
}
