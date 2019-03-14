package com.damon.product.domain.sku.aggregate;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * 商品SKU
 * @author Damon S.
 */
@Value
@Builder
public class ProductSku {
    private final String      skuCode;
    private final List<Long>  specIds;
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
