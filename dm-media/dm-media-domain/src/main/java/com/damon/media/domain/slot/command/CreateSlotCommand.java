package com.damon.media.domain.slot.command;

import com.damon.media.domain.slot.aggregate.SlotId;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.OSCategory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class CreateSlotCommand {
    private final SlotId slotId;
    private final String name;
    private final SlotType type;
    private final OSCategory os;
    private final String channel;
    private final String snapshot;
    private final String blockIndustry;
    private final String description;
    private final List<Long> appIds;
    private final Long specId;
    private final Long userId;
    private final Long createdBy;
}
