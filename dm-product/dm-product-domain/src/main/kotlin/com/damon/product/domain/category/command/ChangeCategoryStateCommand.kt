package com.damon.product.domain.category.command

import com.damon.product.domain.category.aggregate.CategoryId
import org.axonframework.modelling.command.TargetAggregateIdentifier

/**
 * 品类状态变更命令集
 * @author Damon S.
 * @date 2019年02月28日 22:22
 * @version v1.0.1
 */
abstract class ChangeCategoryStateCommand (
        @TargetAggregateIdentifier
        val categoryId: CategoryId,
        val updatedBy: Long
)
class ChangeCategoryShowStateCommand(categoryId: CategoryId, updatedBy: Long)
    : ChangeCategoryStateCommand(categoryId, updatedBy)

class ChangeCategoryNavStateCommand(categoryId: CategoryId, updatedBy: Long)
    : ChangeCategoryStateCommand(categoryId, updatedBy)

class RemoveCategoryCommand(categoryId: CategoryId, updatedBy: Long)
    : ChangeCategoryStateCommand(categoryId, updatedBy)

class RecoverCategoryCommand(categoryId: CategoryId, updatedBy: Long)
    : ChangeCategoryStateCommand(categoryId, updatedBy)