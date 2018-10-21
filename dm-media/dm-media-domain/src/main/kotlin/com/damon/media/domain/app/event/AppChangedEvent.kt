package com.damon.media.domain.app.event

import com.damon.media.domain.app.aggregate.AppId
import com.damon.shared.enums.AuditStatus
import com.damon.shared.enums.SwitchState
import org.axonframework.commandhandling.TargetAggregateIdentifier
import java.time.LocalDateTime


abstract class AppStateChangedEvent (
        @TargetAggregateIdentifier
        val appId: AppId,
        val updatedBy: Long,
        val updatedAt: LocalDateTime
)

/**
 * 停启状态变更事件
 */
class AppActivatedEvent(appId: AppId, val state: SwitchState, updatedBy: Long)
    : AppStateChangedEvent(appId, updatedBy, LocalDateTime.now())
class AppDeactivatedEvent(appId: AppId, val state: SwitchState, updatedBy: Long)
    : AppStateChangedEvent(appId, updatedBy, LocalDateTime.now())

/**
 * 审核状态变更事件
 */
class AppPassedEvent(appId: AppId, val status: AuditStatus, updatedBy: Long)
    : AppStateChangedEvent(appId, updatedBy, LocalDateTime.now())
class AppRejectedEvent(appId: AppId, val status: AuditStatus, updatedBy: Long)
    : AppStateChangedEvent(appId, updatedBy, LocalDateTime.now())

