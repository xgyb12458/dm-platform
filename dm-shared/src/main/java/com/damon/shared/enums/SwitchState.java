package com.damon.shared.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 启、停状态
 * 这是一种延续性的状态
 * @author Damon S.
 */
@Getter
@RequiredArgsConstructor
public enum SwitchState {
    /**1开启，2关闭，0不可用*/
    ON(1), OFF(2), NA(0);

    private final Integer value;
}