package com.damon.product.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum VerifyState {
    /**
     * 草稿中
     */
    DRAFTING(0),

    /**
     * 审核中
     */
    AUDITING(1),

    /**
     * 审核通过
     */
    APPROVED(2),

    /**
     * 审核驳回
     */
    REJECTED(3);

    private final int code;

    /**
     * 根据传值转换枚举值。
     */
    public static VerifyState parse(int code) {
        VerifyState state;
        switch (code) {
            case 0:
                state = DRAFTING;
                break;
            case 1:
                state = AUDITING;
                break;
            case 2:
                state = APPROVED;
                break;
            case 3:
                state = REJECTED;
                break;
            default:
                state = DRAFTING;
        }
        return state;
    }
}
