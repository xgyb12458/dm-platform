package com.damon.shared.enums;

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
    public static ReviewState parse(int code) {
        ReviewState state = AUDITING;
        switch (code) {
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
        }
        return state;
    }
}
