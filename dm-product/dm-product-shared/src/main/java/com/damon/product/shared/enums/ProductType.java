package com.damon.product.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品类型
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum ProductType {
    /**
     * 普通商品
     */
    NORMAL,

    /**
     * 套装商品
     */
    SUIT
}
