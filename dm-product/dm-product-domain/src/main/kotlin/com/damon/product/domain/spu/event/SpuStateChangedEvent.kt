package com.damon.product.domain.spu.event

import com.damon.shared.enums.YesNoEnum
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.Instant

/**
 * SPU信息变革事件
 * @author Damon S.
 * @date 2019年02月24日 17:07
 * @version v1.0.1
 */
abstract class SpuStateChangedEvent (
        @TargetAggregateIdentifier
        val spuId: Long,
        val state: YesNoEnum,
        val updatedBy: Long,
        val updatedAt: Instant
)
class SpuNewProductChangedEvent(spuId: Long, state: YesNoEnum, updatedBy: Long)
    : SpuStateChangedEvent(spuId, state, updatedBy, Instant.now())

class SpuRecommendedChangedEvent(spuId: Long, state: YesNoEnum, updatedBy: Long)
    : SpuStateChangedEvent(spuId, state, updatedBy, Instant.now())

class SpuSoldOutChangedEvent(spuId: Long, state: YesNoEnum, updatedBy: Long)
    : SpuStateChangedEvent(spuId, state, updatedBy, Instant.now())

class SpuCanReturnChangedEvent(spuId: Long, state: YesNoEnum, updatedBy: Long)
    : SpuStateChangedEvent(spuId, state, updatedBy, Instant.now())

class SpuRemovedEvent(spuId: Long, updatedBy: Long)
    : SpuStateChangedEvent(spuId, YesNoEnum.Y, updatedBy, Instant.now())

class SpuRecoveredEvent(spuId: Long, updatedBy: Long)
    : SpuStateChangedEvent(spuId, YesNoEnum.N, updatedBy, Instant.now())