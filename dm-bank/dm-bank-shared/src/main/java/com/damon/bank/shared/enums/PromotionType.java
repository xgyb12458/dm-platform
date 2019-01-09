package com.damon.bank.shared.enums;

import lombok.Getter;

/**
 * 活动类型
 * @author Damon S.
 */
@Getter
public enum PromotionType {
    /**
     * 无活动
     */
    NA,

    /**
     * 拼团
     */
    PIECE,

    /**
     * 闪购
     */
    FLASH,

    /**
     * 买赠
     */
    MEETING
}
