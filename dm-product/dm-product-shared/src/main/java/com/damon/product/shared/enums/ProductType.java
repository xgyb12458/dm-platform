package com.damon.product.shared.enums;

import lombok.Getter;

/**
 * 订单类型
 * @author Damon S.
 */
@Getter
public enum ProductType {
    /**
     * 普通订单
     */
    NORMAL,

    /**
     * 闪购
     */
    FLASH,

    /**
     * VIP权益
     */
    VIP,

    /**
     * 拼团
     */
    PIECE,

    /**
     * 大会
     */
    MEETING
}
