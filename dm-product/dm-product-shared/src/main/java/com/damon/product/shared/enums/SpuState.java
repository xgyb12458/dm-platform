package com.damon.product.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum SpuState {
    /**
     * 草稿箱
     */
    DRAFT(0),

    /**
     * 计划发布
     */
    FORSALE(1),

    /**
     * 上架
     */
    ONSALE(2),

    /**
     * 下架
     */
    INSTOCK(3);

    private final int code;

    /**
     * 根据传值转换枚举值。
     */
    public static SpuState parse(int code) {
        SpuState state;
        switch (code) {
            case 0:
                state = DRAFT;
                break;
            case 3:
                state = INSTOCK;
                break;
            case 2:
                state = ONSALE;
                break;
            case 1:
                state = FORSALE;
                break;
            default:
                state = DRAFT;
        }
        return state;
    }
}
