package com.damon.oauth.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态枚举
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum UserState {

    /**
     * 正常
     */
    ACTIVE,

    /**
     * 冻结
     */
    FROZEN,

    /**
     * 删除
     */
    REMOVED;
}
