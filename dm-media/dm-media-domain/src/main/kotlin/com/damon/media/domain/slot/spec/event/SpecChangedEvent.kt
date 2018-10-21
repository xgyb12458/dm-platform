package com.damon.media.domain.slot.spec.event

import com.damon.media.domain.slot.spec.aggregate.SpecId
import com.damon.shared.enums.SwitchState
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.sql.Timestamp

abstract class SpecStateChangedEvent (
        @TargetAggregateIdentifier
        val specId: SpecId,
        val state: SwitchState,
        val updatedBy: Long,
        val updateTime: Timestamp
)

/**
 * 停启状态变更事件
 */
class SpecActivatedEvent(specId: SpecId, state: SwitchState, updatedBy: Long, updatedTime: Timestamp)
    : SpecStateChangedEvent(specId, state, updatedBy, updatedTime)
class SpecDeactivatedEvent(specId: SpecId, state: SwitchState, updatedBy: Long, updatedTime: Timestamp)
    : SpecStateChangedEvent(specId, state, updatedBy, updatedTime)

