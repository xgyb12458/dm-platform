package com.damon.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统类型
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum OSCategory {
    /** 系统类型：苹果，安卓，其它，不可用 */
    IOS(1),
    ANDROID(2),
    OTHERS(4),
    NA(0);

    private final Integer value;
}
