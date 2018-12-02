package com.damon.oauth.domain.user.aggregate;

import lombok.Builder;
import lombok.Getter;

/**
 * 订单商品信息
 * @author Damon S.
 */
@Getter
@Builder
public class OrderSku {
    private final CartItemId cartItemId;
    private final SkuId skuId;
    private final Integer quantity;
    private final Long promotionId;
    private final Long detailId;
}
