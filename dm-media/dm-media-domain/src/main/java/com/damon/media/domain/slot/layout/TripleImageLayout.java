package com.damon.media.domain.slot.layout;

import com.damon.media.shared.enums.LayoutType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 信息流三小图
 * @author Damon S.
 */
@Getter
@AllArgsConstructor
public class TripleImageLayout implements FeedLayout<TripleImageLayout> {
    private final Long layoutId;
    private final Integer imageWidth;
    private final Integer imageHeight;
    private final String snapshot;

    @Override
    public LayoutType getLayoutType() {
        return LayoutType.TRIPLE_IMAGE;
    }

    @Override
    public Integer getImageCount() { return getLayoutType().getImageCount();}
}
