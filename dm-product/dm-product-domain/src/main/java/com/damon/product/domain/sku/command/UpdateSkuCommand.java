package com.damon.product.domain.sku.command;

import com.damon.product.domain.sku.aggregate.SkuId;
import com.damon.product.domain.spu.aggregate.SpuId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

/**
 * 更新SKU命令
 * @author Damon S.
 */
@Value
@Builder
public class UpdateSkuCommand {
    @TargetAggregateIdentifier
    private final SkuId       skuId;
    private final SpuId       spuId;
    private final List<Long>  specIds;
    private final String      skuCode;
    private final String      name;
    private final List<Long>  images;
    private final Integer     inventory;
    private final Integer     secureInventory;
    private final Long        price;
    private final Long        reduction;
    private final Long        promoteFee;
    private final Long        serviceFee;
    private final Long        exchangePrice;
    private final Long        exchangePoint;
    private final Long        netWorth;
    private final String      barCode;
    private final Long        createdBy;
}
