package com.damon.media.domain.slot.layout;

import com.damon.media.shared.enums.LayoutType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 一图一文混合样式
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public class ImageTextLayout implements FeedLayout<ImageTextLayout> {
    private final Long layoutId;
    private final Integer imageWidth;
    private final Integer imageHeight;
    private final String snapshot;

    @Override
    public LayoutType getLayoutType() {
        return LayoutType.IMAGE_TEXT;
    }

    @Override
    public Integer getImageCount() { return getLayoutType().getImageCount();}
}
