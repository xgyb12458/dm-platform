package com.damon.product.domain.spu.command

import com.damon.product.domain.spu.aggregate.SpuId
import org.axonframework.modelling.command.TargetAggregateIdentifier

/**
 * SPU状态变更命令集
 * @author Damon S.
 * @date 2019年02月24日 16:49
 * @version v1.0.1
 */
abstract class ChangeSpuStateCommand (
        @TargetAggregateIdentifier
        val spuId: SpuId,
        val updatedBy: Long
)
class ChangeSpuNewProductCommand(spuId: SpuId, updatedBy: Long)
    : ChangeSpuStateCommand(spuId, updatedBy)

class ChangeSpuRecommendedCommand(spuId: SpuId, updatedBy: Long)
    : ChangeSpuStateCommand(spuId, updatedBy)

class ChangeSpuSoldOutCommand(spuId: SpuId, updatedBy: Long)
    : ChangeSpuStateCommand(spuId, updatedBy)

class ChangeSpuSupportReturnCommand(spuId: SpuId, updatedBy: Long)
    : ChangeSpuStateCommand(spuId, updatedBy)

class RemoveSpuCommand(spuId: SpuId, updatedBy: Long)
    : ChangeSpuStateCommand(spuId, updatedBy)

class RecoverSpuCommand(spuId: SpuId, updatedBy: Long)
    : ChangeSpuStateCommand(spuId, updatedBy)
