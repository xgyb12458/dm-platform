package com.damon.product.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 产品状态
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum ProductState {

    /**
     * 草稿箱
     */
    IN_DRAFT,

    /**
     * 计划发布
     */
    FOR_SALE,

    /**
     * 上架
     */
    ON_SALE,

    /**
     * 下架
     */
    IN_STOCK
}
