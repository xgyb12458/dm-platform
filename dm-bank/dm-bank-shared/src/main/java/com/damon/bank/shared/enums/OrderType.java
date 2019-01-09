package com.damon.bank.shared.enums;

import lombok.Getter;

/**
 * 订单类型
 * @author Damon S.
 */
@Getter
public enum OrderType {
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
     * 买赠
     */
    MEETING
}
