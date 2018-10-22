package com.damon.media.domain.slot.layout.command;

import com.damon.media.domain.slot.layout.aggregate.LayoutId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


/**
 * @author Damon S.
 */
@Getter
@Builder
public class UpdateLayoutCommand {
    @TargetAggregateIdentifier
    private final LayoutId layoutId;
    private final Integer width;
    private final Integer height;
    private final String snapshot;
    private final Long updatedBy;
}
