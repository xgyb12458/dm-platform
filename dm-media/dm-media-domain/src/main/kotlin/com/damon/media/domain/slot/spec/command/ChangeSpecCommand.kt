package com.damon.media.domain.slot.spec.command

import com.damon.media.domain.slot.spec.aggregate.SpecId
import com.damon.shared.enums.SwitchState
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.sql.Timestamp
import java.time.Instant

/**
 * 停启状态变更命令
 */
abstract class ChangeSpecStateCommand (
        @TargetAggregateIdentifier
        val specId: SpecId,
        val state: SwitchState,
        val updatedBy: Long,
        val updateTime: Timestamp
)

class ActivateSpecCommand(specId: SpecId, updatedBy: Long)
    : ChangeSpecStateCommand(specId, SwitchState.ON, updatedBy, Timestamp.from(Instant.now()))
class DeactivateSpecCommand(specId: SpecId, updatedBy: Long)
    : ChangeSpecStateCommand(specId, SwitchState.OFF, updatedBy, Timestamp.from(Instant.now()))