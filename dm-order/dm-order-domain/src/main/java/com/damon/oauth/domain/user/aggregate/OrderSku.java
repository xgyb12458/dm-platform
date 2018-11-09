package com.damon.oauth.domain.user.aggregate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderSku {
    private final SkuId skuId;
}
