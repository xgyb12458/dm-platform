package com.damon.media.domain.slot.spec.event;

import com.damon.media.domain.slot.spec.aggregate.SpecExtension;
import com.damon.media.domain.slot.spec.aggregate.SpecId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class SpecUpdatedEvent {
    @TargetAggregateIdentifier
    private final SpecId specId;
    private final Integer width;
    private final Integer height;
    private final String imageType;
    private final Integer imageSize;
    private final List<Long> layoutIds;
    private final String snapshot;
    private final SpecExtension ext;
    private final Long updatedBy;
    private final Timestamp updatedAt;
}
