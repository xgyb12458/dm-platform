package com.damon.product.domain.spu.event

import com.damon.product.domain.spu.aggregate.SpuId
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
        val spuId: SpuId,
        val state: VerifyState,
        val updatedBy: Long,
        val updatedAt: Instant
)

/**
 * 初始化审核状态，草稿中
 */
class SpuVerificationResettedEvent(spuId: SpuId, updatedBy: Long)
    : SpuVerifiedEvent(spuId, VerifyState.DRAFTING, updatedBy, Instant.now())

/**
 * 提交审核
 */
class SpuCommittedEvent(spuId: SpuId, updatedBy: Long)
    : SpuVerifiedEvent(spuId, VerifyState.AUDITING, updatedBy, Instant.now())

/**
 * 审核通过
 */
class SpuApprovedEvent(spuId: SpuId, updatedBy: Long)
    : SpuVerifiedEvent(spuId, VerifyState.APPROVED, updatedBy, Instant.now())

/**
 * 驳回审核
 */
class SpuRejectedEvent(spuId: SpuId, updatedBy: Long)
    : SpuVerifiedEvent(spuId, VerifyState.REJECTED, updatedBy, Instant.now())