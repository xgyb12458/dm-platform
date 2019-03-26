package com.damon.oauth.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum UserType {

    /**
     * User is active.
     */
    NORMAL,

    /**
     * User is frozen, no action can be performed.
     */
    UNKNOWN,

    /**
     * User is removed, the user is not exist any more.
     */
    OTHERS
}
