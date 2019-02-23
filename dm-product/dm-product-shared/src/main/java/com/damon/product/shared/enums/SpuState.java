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
    DRAFT,

    /**
     * 计划发布
     */
    FORSALE,

    /**
     * 上架
     */
    ONSALE,

    /**
     * 下架
     */
    INSTOCK
}
