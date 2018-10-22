package com.damon.media.domain.slot.event

import com.damon.media.domain.slot.aggregate.SlotId
import com.damon.shared.enums.AuditStatus
import com.damon.shared.enums.SwitchState
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.sql.Timestamp

abstract class SlotStateChangedEvent (
        @TargetAggregateIdentifier
        val slotId: SlotId,
        val updatedBy: Long,
        val updatedAt: Timestamp
)
/**
 * 停启状态变更事件
 */
class SlotActivatedEvent(slotId: SlotId, val state: SwitchState, updatedBy: Long, updatedAt: Timestamp)
    : SlotStateChangedEvent(slotId, updatedBy, updatedAt)
class SlotDeactivatedEvent(slotId: SlotId, val state: SwitchState, updatedBy: Long, updatedAt: Timestamp)
    : SlotStateChangedEvent(slotId, updatedBy, updatedAt)

/**
 * 审核状态变更事件
 */
class SlotPassedEvent(slotId: SlotId, val status: AuditStatus, updatedBy: Long, updatedAt: Timestamp)
    : SlotStateChangedEvent(slotId, updatedBy, updatedAt)
class SlotRejectedEvent(slotId: SlotId, val status: AuditStatus, updatedBy: Long, updatedAt: Timestamp)
    : SlotStateChangedEvent(slotId, updatedBy, updatedAt)

