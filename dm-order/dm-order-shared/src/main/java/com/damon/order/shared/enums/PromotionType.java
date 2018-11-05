package com.damon.order.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 活动类型
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum PromotionType {
    /**
     * 无活动
     */
    NORMAL,

    /**
     * 拼团
     */
    PIECE,

    /**
     * 大会活动
     */
    MEETING;
}
