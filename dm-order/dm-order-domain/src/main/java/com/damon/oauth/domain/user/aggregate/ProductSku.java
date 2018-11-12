package com.damon.oauth.domain.user.aggregate;

import lombok.Builder;
import lombok.Getter;

/**
 * 单品信息
 * @author Damon S.
 */
@Getter
@Builder
public class ProductSku {
    private final CartItemId cartItemId;
    private final SkuId skuId;
    private final Integer quantity;
    private final Long promotionId;
    private final Long detailId;
}
