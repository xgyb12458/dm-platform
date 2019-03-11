package com.damon.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月10日 21:02
 */
@Getter
@AllArgsConstructor
public enum OperateType {
    /**
     * 新建
     */
    CREATE,

    /**
     * 更新
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 查询
     */
    QUERY
}
