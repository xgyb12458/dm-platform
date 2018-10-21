package com.damon.media.domain.slot.event;

import com.damon.media.domain.slot.aggregate.SlotId;
import com.damon.media.domain.slot.spec.aggregate.SpecId;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.AuditStatus;
import com.damon.shared.enums.OSCategory;
import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.commandhandling.model.AggregateIdentifier;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class SlotCreatedEvent {
    @AggregateIdentifier
    private final SlotId slotId;
    private final String name;
    private final String alias;
    private final SlotType type;
    private final SwitchState state;
    private final AuditStatus status;
    private final OSCategory os;
    private final String channel;
    private final String snapshot;
    private final String blockIndustry;
    private final String description;
    private final List<Long> appIds;
    private final SpecId specId;
    private final Long userId;
    private final Long createdBy;
    private final Timestamp createdAt;
}
