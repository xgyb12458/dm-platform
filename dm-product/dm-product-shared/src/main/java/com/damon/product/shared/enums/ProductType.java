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
    NORMAL(0),

    /**
     * 套装商品
     */
    SUIT(1);

    private final int code;

    /**
     * 根据传值转换枚举值。
     */
    public static ProductType parse(int code) {
        ProductType type;
        switch (code) {
            case 0:
                type = NORMAL;
                break;
            case 1:
                type = SUIT;
                break;
            default:
                type = NORMAL;
        }
        return type;
    }
}
