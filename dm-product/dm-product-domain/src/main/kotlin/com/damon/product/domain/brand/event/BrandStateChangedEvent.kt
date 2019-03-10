package com.damon.product.domain.brand.event

import com.damon.product.domain.brand.aggregate.BrandId
import com.damon.shared.enums.YesNoEnum
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.Instant

/**
 * 品牌信息变革事件
 * @author Damon S.
 * @date 2019年02月24日 17:07
 * @version v1.0.1
 */
abstract class BrandStateChangedEvent (
        @TargetAggregateIdentifier
        val brandId: BrandId,
        val state: YesNoEnum,
        val updatedBy: Long,
        val updatedAt: Instant
)
class BrandDisplayChangedEvent(brandId: BrandId, state: YesNoEnum, updatedBy: Long)
    : BrandStateChangedEvent(brandId, state, updatedBy, Instant.now())

class BrandFactoryChangedEvent(brandId: BrandId, state: YesNoEnum, updatedBy: Long)
    : BrandStateChangedEvent(brandId, state, updatedBy, Instant.now())

class BrandRemovedEvent(brandId: BrandId, updatedBy: Long)
    : BrandStateChangedEvent(brandId, YesNoEnum.Y, updatedBy, Instant.now())

class BrandRecoveredEvent(brandId: BrandId, updatedBy: Long)
    : BrandStateChangedEvent(brandId, YesNoEnum.N, updatedBy, Instant.now())