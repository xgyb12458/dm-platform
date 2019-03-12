package com.damon.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作日志对象枚举
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月12日 12:45
 */
@Getter
@AllArgsConstructor
public enum OperateTarget {
    /**品牌*/
    BRAND,

    /**类别*/
    CATEGORY,

    /**供应商*/
    SUPPLIER,

    /**商品*/
    PRODUCT
}
