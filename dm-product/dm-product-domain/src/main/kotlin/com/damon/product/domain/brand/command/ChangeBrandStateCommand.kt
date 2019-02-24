package com.damon.product.domain.brand.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

/**
 * 品牌状态变更命令集
 * @author Damon S.
 * @date 2019年02月24日 16:49
 * @version v1.0.1
 */
abstract class ChangeBrandStateCommand (
        @TargetAggregateIdentifier
        val brandId: Long,
        val updatedBy: Long
)
class ChangeBrandDisplayCommand(brandId: Long, updatedBy: Long)
    : ChangeBrandStateCommand(brandId, updatedBy)

class ChangeBrandFactoryCommand(brandId: Long, updatedBy: Long)
    : ChangeBrandStateCommand(brandId, updatedBy)

class DeleteBrandCommand(brandId: Long, updatedBy: Long)
    : ChangeBrandStateCommand(brandId, updatedBy)

class RecoverBrandCommand(brandId: Long, updatedBy: Long)
    : ChangeBrandStateCommand(brandId, updatedBy)