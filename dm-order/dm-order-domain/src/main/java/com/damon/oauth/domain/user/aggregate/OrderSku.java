package com.damon.oauth.domain.user.aggregate;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class OrderSku {

    private final ProductSku sku;

    private final Integer quantity;

    private final String promotionId;

    private final String promotionDetailId;
}
