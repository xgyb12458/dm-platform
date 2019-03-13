package com.damon.product.domain.log.aggregate;

import lombok.Builder;
import lombok.Value;

/**
 * 操作日志内容
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月12日 23:32
 */
@Value
@Builder
public class OperateContent {
    /**修改字段*/
    private final String field;
    /**原始值*/
    private final Object origin;
    /**当前新值*/
    private final Object present;
}
