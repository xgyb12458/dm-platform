package com.damon.media.domain.slot.spec.command;

import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class QuerySpecCommand {
    private final Integer width;
    private final Integer height;
    private final SwitchState state;
    private final SlotType type;
    private final String imageType;
    private final Integer pageSize;
    private final Integer pageIndex;
}
