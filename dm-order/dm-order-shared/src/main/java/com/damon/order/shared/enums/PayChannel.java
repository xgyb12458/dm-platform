package com.damon.order.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付渠道
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum PayChannel {
    /**
     * 支付宝：APP
     */
    ALIPAY,

    /**
     * 微信支付：网页
     */
    WECHAT,

    /**
     * 微信支付：APP
     */
    WECHAT_APP,

    /**
     * 微信支付：小程序
     */
    WECHAT_MINI,

    /**
     * 银联支付
     */
    UNION_PAY
}
