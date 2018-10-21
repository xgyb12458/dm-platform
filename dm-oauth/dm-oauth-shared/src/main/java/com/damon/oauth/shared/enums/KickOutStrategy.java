package com.damon.oauth.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Damon
 */
@Getter
@AllArgsConstructor
public enum KickOutStrategy {
    /***踢出策略*/
    FIFO(1, "踢出先登录的用户"),
    LIFO(2, "踢出后登录的用户");

    private Integer value;
    private String message;
}
