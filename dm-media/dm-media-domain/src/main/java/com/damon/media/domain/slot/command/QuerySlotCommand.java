package com.damon.media.domain.slot.command;

import com.damon.shared.enums.AuditStatus;
import com.damon.shared.enums.OSCategory;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class QuerySlotCommand {
    private final Long appId;
    private final String name;
    private final String alias;
    private final String channel;
    private final OSCategory os;
    private final Long specId;
    private final Long userId;
    private final SwitchState state;
    private final SlotType type;
    private final AuditStatus status;
    private final Integer pageSize;
    private final Integer pageIndex;
}
