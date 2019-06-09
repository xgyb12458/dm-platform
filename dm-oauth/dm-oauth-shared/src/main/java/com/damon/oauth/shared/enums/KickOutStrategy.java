package com.damon.oauth.shared.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 踢出策略
 * @author Damon
 */
@Getter
@RequiredArgsConstructor
public enum KickOutStrategy {
    /***踢出策略*/
    FIFO(1, "先进先出策略"),
    LIFO(2, "后进先出策略");

    private final Integer value;
    private final String message;
}
