package com.damon.product.domain.spu.event

import com.damon.product.shared.enums.VerifyState
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.Instant

/**
 * SPu审核事件集
 * @author Damon S.
 * @date 2019年02月24日 23:20
 * @version v1.0.1
 */
abstract class SpuVerifiedEvent (
        @TargetAggregateIdentifier
        val spuId: Long,
        val state: VerifyState,
        val updatedBy: Long,
        val updatedAt: Instant
)

/**
 * 初始化审核状态，草稿中
 */
class SpuVerificationResettedEvent(spuId: Long, updatedBy: Long)
    : SpuVerifiedEvent(spuId, VerifyState.DRAFTING, updatedBy, Instant.now())

/**
 * 提交审核
 */
class SpuCommittedEvent(spuId: Long, updatedBy: Long)
    : SpuVerifiedEvent(spuId, VerifyState.AUDITING, updatedBy, Instant.now())

/**
 * 审核通过
 */
class SpuApprovedEvent(spuId: Long, updatedBy: Long)
    : SpuVerifiedEvent(spuId, VerifyState.APPROVED, updatedBy, Instant.now())

/**
 * 驳回审核
 */
class SpuRejectedEvent(spuId: Long, updatedBy: Long)
    : SpuVerifiedEvent(spuId, VerifyState.REJECTED, updatedBy, Instant.now())