package com.damon.media.domain.slot.spec;

import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import lombok.Getter;

/***
 * 开屏资源位规格
 * @author Damon S.
 */
@Getter
public class SplashSpecification extends SlotSpecification<SplashSpecification> {
    private final String lookAndFeel;


    public SplashSpecification(Long slotId,
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
        return SlotType.SPLASH;
    }
}
