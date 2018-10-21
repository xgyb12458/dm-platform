package com.damon.media.domain.app.command

import com.damon.media.domain.app.aggregate.AppId
import com.damon.shared.enums.AuditStatus
import com.damon.shared.enums.SwitchState
import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * 停启状态变更命令
 */
abstract class ChangeAppStateCommand (
        @TargetAggregateIdentifier
        val appId: AppId,
        val state: SwitchState,
        val updatedBy: Long
)
class ActivateAppCommand(appId: AppId, updatedBy: Long)
    : ChangeAppStateCommand(appId, SwitchState.ON, updatedBy)
class DeactivateAppCommand(appId: AppId, updatedBy: Long)
    : ChangeAppStateCommand(appId, SwitchState.OFF, updatedBy)

/**
 * 审核状态变更命令
 */
abstract class ChangeAppStatusCommand (
        @TargetAggregateIdentifier
        val appId: AppId,
        val status: AuditStatus,
        val updatedBy: Long
)
class PassAppCommand(appId: AppId, updatedBy: Long)
    : ChangeAppStatusCommand(appId, AuditStatus.APPROVED, updatedBy)
class RejectAppCommand(appId: AppId, updatedBy: Long)
    : ChangeAppStatusCommand(appId, AuditStatus.REJECTED, updatedBy)
