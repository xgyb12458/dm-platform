package com.damon.media.domain.slot.spec;

import com.damon.media.domain.slot.layout.aggregate.LayoutId;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import lombok.Getter;

/***
 * 信息流规格
 * @author Damon S.
 */
@Getter
public class FeedSpecification
        extends BaseSlotSpecification<FeedSpecification> {
    private final LayoutId layoutId;


    public FeedSpecification(Long slotId,
                             Integer width,
                             Integer height,
                             String imageType,
                             Integer imageSize,
                             String snapshot,
                             String state,
                             Long layoutId) {
        super(slotId, width, height, imageType, imageSize, snapshot, SwitchState.valueOf(state));
        this.layoutId = new LayoutId(layoutId);
    }

    @Override
    public Integer getImageSize() {
        throw new RuntimeException("Not Supported Exception.");
    }

    @Override
    public String getSnapshot() {
        throw new RuntimeException("Not Supported Exception.");
    }

    @Override
    public SlotType getType() {
        return SlotType.FEEDS;
    }
}
