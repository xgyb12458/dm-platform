package com.damon.media.domain.slot.layout;

import com.damon.media.shared.enums.LayoutType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 一大图信息流样式
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public class FullImageLayout implements FeedLayout<FullImageLayout> {
    private final Long layoutId;
    private final Integer imageWidth;
    private final Integer imageHeight;
    private final String snapshot;

    @Override
    public LayoutType getLayoutType() {
        return LayoutType.FULL_IMAGE;
    }

    @Override
    public Integer getImageCount() { return getLayoutType().getImageCount();}
}
