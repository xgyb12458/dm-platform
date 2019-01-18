package com.damon.product.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum ReviewState {

    /**
     * 审核中
     */
    AUDITING,

    /**
     * 审核通过
     */
    APPROVED,

    /**
     * 审核驳回
     */
    REJECTED
}
