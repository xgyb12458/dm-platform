package com.damon.media.domain.slot.layout.command;

import com.damon.media.domain.slot.layout.aggregate.LayoutId;
import com.damon.media.shared.enums.LayoutType;
import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;


/**
 * @author Damon S.
 */
@Getter
@Builder
public class CreateLayoutCommand {
    private final LayoutId layoutId;
    private final Integer width;
    private final Integer height;
    private final SwitchState state;
    private final LayoutType type;
    private final String snapshot;
    private final Long createdBy;
}
