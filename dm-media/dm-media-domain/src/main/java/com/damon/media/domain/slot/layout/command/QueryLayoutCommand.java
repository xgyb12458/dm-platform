package com.damon.media.domain.slot.layout.command;

import com.damon.media.shared.enums.LayoutType;
import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class QueryLayoutCommand {
    private final Integer width;
    private final Integer height;
    private final SwitchState state;
    private final LayoutType type;
    private final Integer pageSize;
    private final Integer pageIndex;
}
