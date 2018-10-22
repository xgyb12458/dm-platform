package com.damon.media.domain.slot.command

import com.damon.media.domain.slot.aggregate.SlotId
import com.damon.shared.enums.AuditStatus
import com.damon.shared.enums.SwitchState
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.sql.Timestamp
import java.time.Instant

/**
 * 停启状态变更命令
 */
abstract class ChangeSlotStateCommand (
        @TargetAggregateIdentifier
        val slotId: SlotId,
        val state: SwitchState,
        val updatedBy: Long,
        val updatedAt: Timestamp
)
class ActivateSlotCommand(slotId: SlotId, updatedBy: Long)
    : ChangeSlotStateCommand(slotId, SwitchState.ON, updatedBy, Timestamp.from(Instant.now()))
class DeactivateSlotCommand(slotId: SlotId, updatedBy: Long)
    : ChangeSlotStateCommand(slotId, SwitchState.OFF, updatedBy, Timestamp.from(Instant.now()))

/**
 * 审核状态变更命令
 */
abstract class ChangeSlotStatusCommand (
        @TargetAggregateIdentifier
        val slotId: SlotId,
        val status: AuditStatus,
        val updatedBy: Long,
        val updatedAt: Timestamp
)
class PassSlotCommand(slotId: SlotId, updatedBy: Long)
    : ChangeSlotStatusCommand(slotId, AuditStatus.APPROVED, updatedBy, Timestamp.from(Instant.now()))
class RejectSlotCommand(slotId: SlotId, updatedBy: Long)
    : ChangeSlotStatusCommand(slotId, AuditStatus.REJECTED, updatedBy, Timestamp.from(Instant.now()))
