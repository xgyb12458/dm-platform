package com.damon.product.domain.category.event

import com.damon.product.domain.category.aggregate.CategoryId
import com.damon.shared.enums.YesNoEnum
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.Instant

/**
 * 品类信息变革事件
 * @author Damon S.
 * @date 2019年02月28日 22:23
 * @version v1.0.1
 */
abstract class CategoryStateChangedEvent (
        @TargetAggregateIdentifier
        val categoryId: CategoryId,
        val state: YesNoEnum,
        val updatedBy: Long,
        val updatedAt: Instant
)
class CategoryShowStateChangedEvent(categoryId: CategoryId, state: YesNoEnum, updatedBy: Long)
    : CategoryStateChangedEvent(categoryId, state, updatedBy, Instant.now())

class CategoryNavStateChangedEvent(categoryId: CategoryId, state: YesNoEnum, updatedBy: Long)
    : CategoryStateChangedEvent(categoryId, state, updatedBy, Instant.now())

class CategoryRemovedEvent(categoryId: CategoryId, updatedBy: Long)
    : CategoryStateChangedEvent(categoryId, YesNoEnum.Y, updatedBy, Instant.now())

class CategoryRecoveredEvent(categoryId: CategoryId, updatedBy: Long)
    : CategoryStateChangedEvent(categoryId, YesNoEnum.N, updatedBy, Instant.now())