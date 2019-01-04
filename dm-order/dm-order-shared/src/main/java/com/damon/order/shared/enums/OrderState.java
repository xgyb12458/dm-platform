package com.damon.order.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum OrderState {

    /**
     * 未知，不可用
     */
    NA,

    /**
     * 已生成订单，待支付
     */
    SUBMITTED,

    /**
     * 已支付，待发货
     */
    PAID,

    /**
     * 已支付，无库存
     */
    HOLD,

    /**
     * 已发货，待收货
     */
    SHIPPING,

    /**
     * 交易成功，待评价
     */
    COMPLETED,

    /**
     * 已评价
     */
    COMMENTED,

    /**
     * 退款中
     */
    REFUNDING,

    /**
     * 已退款
     */
    REFUNDED,

    /**
     * 订单已取消，交易关闭
     */
    CANCELLED,

    /**
     * 完成售后，关闭
     */
    CLOSED
}
