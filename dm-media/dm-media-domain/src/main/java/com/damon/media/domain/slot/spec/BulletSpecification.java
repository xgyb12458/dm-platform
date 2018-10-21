package com.damon.media.domain.slot.spec;

import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import lombok.Getter;

/***
 * 弹层规格
 * @author Damon S.
 */
@Getter
public class BulletSpecification extends SlotSpecification<BulletSpecification> {
    private final String lookAndFeel;


    public BulletSpecification(Long slotId,
                               Integer width,
                               Integer height,
                               String imageType,
                               Integer imageSize,
                               String snapshot,
                               String state,
                               String lookAndFeel) {
        super(slotId, width, height, imageType, imageSize, snapshot, SwitchState.valueOf(state));
        this.lookAndFeel = lookAndFeel;
    }

    @Override
    public SlotType getType() {
        return SlotType.BULLET;
    }
}
