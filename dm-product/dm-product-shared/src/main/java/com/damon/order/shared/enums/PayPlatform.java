package com.damon.order.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付渠道
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum PayPlatform {
    /**
     * 支付宝
     */
    ALIPAY,

    /**
     * 微信支付
     */
    WECHAT,

    /**
     * 银联支付
     */
    UNIONPAY,

    /**
     * 信用卡支付
     */
    CREDIT
}
