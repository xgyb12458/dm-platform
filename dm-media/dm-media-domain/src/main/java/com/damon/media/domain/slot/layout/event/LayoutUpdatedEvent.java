package com.damon.media.domain.slot.layout.event;


import com.damon.media.domain.slot.layout.aggregate.LayoutId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.sql.Timestamp;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class LayoutUpdatedEvent {
    @TargetAggregateIdentifier
    private final LayoutId layoutId;
    private final Integer width;
    private final Integer height;
    private final String snapshot;
    private final Long updatedBy;
    private final Timestamp updatedAt;
}
