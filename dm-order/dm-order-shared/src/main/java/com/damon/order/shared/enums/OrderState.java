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
     * 生成订单，待付款
     */
    CREATED,

    /**
     * 已付款，待发货
     */
    PAID,

    /**
     * 已支付无库存
     */
    PAIDNOSTOCK,

    /**
     * 已发货，待收货
     */
    DELIVERY,

    /**
     * 已收货
     */
    SHIPPED,

    /**
     * 订单取消
     */
    CANCELLED,

    /**
     * 订单完成
     */
    FINISHED;
}
