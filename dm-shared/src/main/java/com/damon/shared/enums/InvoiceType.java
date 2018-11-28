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
    NA,

    /**个人发票*/
    PERSONAL,

    /**增值税普通发票*/
    VAT_ORDINARY,

    /**增值税专用发票*/
    VAT_SPECIAL
}
