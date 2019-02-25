package com.damon.product.domain.spu.command

import com.damon.product.domain.spu.aggregate.SpuId
import org.axonframework.modelling.command.TargetAggregateIdentifier

/**
 * SPu审核命令集
 * @author Damon S.
 * @date 2019年02月24日 23:13
 * @version v1.0.1
 */
abstract class VerifySpuCommand (
        @TargetAggregateIdentifier
        val spuId: SpuId,
        val updatedBy: Long
)

/**
 * 初始化审核状态，草稿中
 */
class ResetSpuVerificationCommand(spuId: SpuId, updatedBy: Long)
    : VerifySpuCommand(spuId, updatedBy)

/**
 * 提交审核
 */
class CommitSpuVerificationCommand(spuId: SpuId, updatedBy: Long)
    : VerifySpuCommand(spuId, updatedBy)

/**
 * 审核通过
 */
class ApproveSpuCommand(spuId: SpuId, updatedBy: Long)
    : VerifySpuCommand(spuId, updatedBy)

/**
 * 驳回审核
 */
class RejectSpuCommand(spuId: SpuId, updatedBy: Long)
    : VerifySpuCommand(spuId, updatedBy)