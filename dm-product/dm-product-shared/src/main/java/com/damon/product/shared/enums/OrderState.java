package com.damon.product.shared.enums;

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
     * 已生成订单，待支付
     */
    SUBMITTED,

    /**
     * 订单已取消，交易关闭
     */
    CANCELLED,

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
    DELIVERED,

    /**
     * 交易成功，待评价
     */
    FINISHED,

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
     * 已删除
     */
    REMOVED
}
