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
