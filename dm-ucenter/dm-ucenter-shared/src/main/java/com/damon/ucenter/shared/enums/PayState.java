package com.damon.ucenter.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum PayState {
    /**
     * 待付款
     */
    UNPAID,

    /**
     * 支付失败
     */
    FAILED,

    /**
     * 支付成功
     */
    SUCCESS
}
