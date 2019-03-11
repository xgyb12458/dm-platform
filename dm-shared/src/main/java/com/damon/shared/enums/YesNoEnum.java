package com.damon.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是与否的枚举类
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum YesNoEnum {
    /***/
    Y(1),
    N(0);

    private final int code;

    /**
     * 根据传值转换枚举值。
     * 小于等于 0 解释为 N 并强制转换为 0，
     * 大于 0 解释为 Y 并强制转换为 1
     */
    public static YesNoEnum parse(int code) {
        return code > 0 ? Y : N;
    }

    /**
     * 将数据库中的状态值转换为数值
     */
    public static int codeOf(String name) {
        return valueOf(name).getCode();
    }
}
