package com.damon.media.domain.slot.spec.event;

import com.damon.media.domain.slot.spec.aggregate.SpecExtension;
import com.damon.media.domain.slot.spec.aggregate.SpecId;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class SpecCreatedEvent {
    @TargetAggregateIdentifier
    private final SpecId specId;
    private final Integer width;
    private final Integer height;
    private final String imageType;
    private final Integer imageSize;
    private final SwitchState state;
    private final SlotType type;
    private final List<Long> layoutIds;
    private final SpecExtension ext;
    private final String snapshot;
    private final Long createdBy;
    private final Timestamp createdAt;
}
