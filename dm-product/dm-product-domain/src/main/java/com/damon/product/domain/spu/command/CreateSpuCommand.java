package com.damon.product.domain.spu.command;

import com.damon.product.domain.sku.aggregate.ProductSku;
import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.SpuState;
import com.damon.product.shared.enums.VerifyState;
import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 创建商品SPU命令参数，包括其下的SKU信息
 * @author Damon S.
 */
@Value
@Builder
public class CreateSpuCommand {
    @TargetAggregateIdentifier
    private final SpuId             spuId;
    @NotNull
    private final String            spuCode;
    @NotNull
    private final String            name;
    private final String            subTitle;
    @NotNull
    private final Long              imageId;
    private final List<Long>        albumImages;
    private final List<ProductSku>  skus;
    @NotNull
    private final Long              price;
    private final Long              weight;
    private final Long              marketPrice;
    private final YesNoEnum         newProduct;
    private final YesNoEnum         recommended;
    private final YesNoEnum         supportReturn;
    private final Integer           inventory;
    private final Integer           safetyStock;
    private final String            model;
    private final ProductType       type;
    private final Long              categoryId;
    private final Long              brandId;
    private final Long              warehouseId;
    private final Long              supplierId;
    private final Long              freightTemplateId;
    private final String            h5Detail;
    private final String            deliveryRegion;
    private final String            description;
    private final Long              createdBy;
}
