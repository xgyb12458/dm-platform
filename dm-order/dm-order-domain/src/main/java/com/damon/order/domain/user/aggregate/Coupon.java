package com.damon.order.domain.user.aggregate;

import lombok.Builder;
import lombok.Getter;

/**
 * 优惠券
 * @author Damon S.
 */
@Getter
@Builder
public class Coupon {
    private final CouponId couponId;
}
