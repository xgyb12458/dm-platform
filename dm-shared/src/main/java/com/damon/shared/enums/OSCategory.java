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

    private final int code;

    /**
     * 根据传值转换枚举值。
     */
    public static OSCategory parse(int code) {
        OSCategory category = NA;
        switch (code) {
            case 0:
                category = NA;
                break;
            case 1:
                category = IOS;
                break;
            case 2:
                category = ANDROID;
                break;
            case 4:
                category = OTHERS;
                break;
            default:
        }
        return category;
    }
}
