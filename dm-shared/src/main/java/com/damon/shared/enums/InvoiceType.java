package com.damon.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发票类型
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum InvoiceType {
    /**不开发票*/
    NONE(0),

    /**个人发票*/
    PERSONAL(1),

    /**增值税普通发票*/
    VAT_ORDINARY(2),

    /**增值税专用发票*/
    VAT_SPECIAL(3);

    private final int code;

    /**
     * 根据传值转换枚举值。
     */
    public static InvoiceType parse(int code) {
        InvoiceType type = NONE;
        switch (code) {
            case 0:
                type = NONE;
                break;
            case 2:
                type = VAT_ORDINARY;
                break;
            case 3:
                type = VAT_SPECIAL;
                break;
            case 1:
                type = PERSONAL;
                break;
            default:
        }
        return type;
    }
}
