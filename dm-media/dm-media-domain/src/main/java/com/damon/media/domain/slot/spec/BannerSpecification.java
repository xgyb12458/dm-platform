package com.damon.media.domain.slot.spec;

import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import lombok.Getter;

/***
 * 轮播图规格
 * @author Damon S.
 */
@Getter
public class BannerSpecification extends SlotSpecification<BannerSpecification> {
    private final Integer frameCount;

    public BannerSpecification(Long slotId,
                               Integer width,
                               Integer height,
                               String imageType,
                               Integer imageSize,
                               String snapshot,
                               String state,
                               Integer frameCount) {
        super(slotId, width, height, imageType, imageSize, snapshot, SwitchState.valueOf(state));
        this.frameCount = frameCount;
    }

    @Override
    public SlotType getType() {
        return SlotType.BANNER;
    }
}
