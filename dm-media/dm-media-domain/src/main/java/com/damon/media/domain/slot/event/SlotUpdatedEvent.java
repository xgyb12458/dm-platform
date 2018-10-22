package com.damon.media.domain.slot.event;


import com.damon.media.domain.slot.aggregate.SlotId;
import com.damon.media.domain.slot.spec.aggregate.SpecId;
import com.damon.shared.enums.OSCategory;
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
public class SlotUpdatedEvent {
    @TargetAggregateIdentifier
    private final SlotId slotId;
    private final String channel;
    private final String snapshot;
    private final OSCategory os;
    private final List<Long> appIds;
    private final String blockIndustry;
    private final String description;
    private final SpecId specId;
    private final Long updatedBy;
    private final Timestamp updateTime;
}
