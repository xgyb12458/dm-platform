package com.damon.media.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 信息流样式类型
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public enum LayoutType {
    /***大图样式，图文样式，三小图样式。20字符以内*/
    FULL_IMAGE(1),
    IMAGE_TEXT(1),
    TRIPLE_IMAGE(3);

    private final Integer imageCount;
}
