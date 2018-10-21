package com.damon.media.domain.slot.layout.command

import com.damon.media.domain.slot.layout.aggregate.LayoutId
import com.damon.shared.enums.SwitchState
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.sql.Timestamp
import java.time.Instant

/**
 * 停启状态变更命令
 */
abstract class ChangeLayoutStateCommand (
        @TargetAggregateIdentifier
        val layoutId: LayoutId,
        val state: SwitchState,
        val updatedBy: Long,
        val updatedAt: Timestamp
)
class ActivateLayoutCommand(layoutId: LayoutId, updatedBy: Long)
    : ChangeLayoutStateCommand(layoutId, SwitchState.ON, updatedBy, Timestamp.from(Instant.now()))
class DeactivateLayoutCommand(layoutId: LayoutId, updatedBy: Long)
    : ChangeLayoutStateCommand(layoutId, SwitchState.OFF, updatedBy, Timestamp.from(Instant.now()))