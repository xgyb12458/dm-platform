package com.damon.product.domain.brand.command

import com.damon.product.domain.brand.aggregate.BrandId
import org.axonframework.modelling.command.TargetAggregateIdentifier

/**
 * 品牌状态变更命令集
 * @author Damon S.
 * @date 2019年02月24日 16:49
 * @version v1.0.1
 */
abstract class ChangeBrandStateCommand (
        @TargetAggregateIdentifier
        val brandId: BrandId,
        val updatedBy: Long
)
class ChangeBrandDisplayCommand(brandId: BrandId, updatedBy: Long)
    : ChangeBrandStateCommand(brandId, updatedBy)

class ChangeBrandFactoryCommand(brandId: BrandId, updatedBy: Long)
    : ChangeBrandStateCommand(brandId, updatedBy)

class RemoveBrandCommand(brandId: BrandId, updatedBy: Long)
    : ChangeBrandStateCommand(brandId, updatedBy)

class RecoverBrandCommand(brandId: BrandId, updatedBy: Long)
    : ChangeBrandStateCommand(brandId, updatedBy)