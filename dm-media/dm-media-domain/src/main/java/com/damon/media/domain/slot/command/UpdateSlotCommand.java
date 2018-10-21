package com.damon.media.domain.slot.command;

import com.damon.media.domain.slot.aggregate.SlotId;
import com.damon.shared.enums.OSCategory;
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
public class UpdateSlotCommand {
    @TargetAggregateIdentifier
    private final SlotId slotId;
    private final String channel;
    private final String snapshot;
    private final OSCategory os;
    private final List<Long> appIds;
    private final String blockIndustry;
    private final String description;
    private final Long specId;
    private final Long updatedBy;
    private final Timestamp updateTime;
}
