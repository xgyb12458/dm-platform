package com.damon.media.domain.slot.layout.event

import com.damon.media.domain.slot.layout.aggregate.LayoutId
import com.damon.shared.enums.SwitchState
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.sql.Timestamp

abstract class LayoutStateChangedEvent (
        @TargetAggregateIdentifier
        val layoutId: LayoutId,
        val updatedBy: Long,
        val updatedAt: Timestamp
)
/**
 * 停启状态变更事件
 */
class LayoutActivatedEvent(layoutId: LayoutId, val state: SwitchState, updatedBy: Long, updatedAt: Timestamp)
    : LayoutStateChangedEvent(layoutId, updatedBy, updatedAt)
class LayoutDeactivatedEvent(layoutId: LayoutId, val state: SwitchState, updatedBy: Long, updatedAt: Timestamp)
    : LayoutStateChangedEvent(layoutId, updatedBy, updatedAt)