package com.damon.bank.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum PaidState {
    /**
     * 待付款
     */
    UNPAID,

    /**
     * 支付失败
     */
    FAILED,

    /**
     * 取消支付
     */
    CANCELLED,

    /**
     * 支付成功
     */
    SUCCESS
}
