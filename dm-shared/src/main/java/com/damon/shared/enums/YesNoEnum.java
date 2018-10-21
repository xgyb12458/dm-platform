package com.damon.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 资源位类型
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum YesNoEnum {
    /***/
    YES(Boolean.TRUE),
    NO(Boolean.FALSE);

    private final Boolean value;
}
