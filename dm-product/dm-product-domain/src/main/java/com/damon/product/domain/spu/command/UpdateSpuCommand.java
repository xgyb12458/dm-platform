package com.damon.product.domain.spu.command;

import com.damon.product.domain.sku.aggregate.ProductSku;
import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.shared.enums.ProductType;
import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

/**
 * 更新商品SPU命令参数，包括其下的SKU信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月24日 17:39
 */
@Value
@Builder
public class UpdateSpuCommand {
    @TargetAggregateIdentifier
    private final SpuId             spuId;
    private final String            name;
    private final String            subTitle;
    private final Long              imageId;
    private final List<Long>        albumImages;
    private final List<ProductSku>  skus;
    private final String            description;
    private final Long              price;
    private final Long              marketPrice;
    private final YesNoEnum         newProduct;
    private final YesNoEnum         recommended;
    private final YesNoEnum         soldOut;
    private final Integer           inventory;
    private final Integer           safetyStock;
    private final String            model;
    private final ProductType       type;
    private final YesNoEnum         supportReturn;
    private final Long              categoryId;
    private final Long              brandId;
    private final Long              warehouseId;
    private final Long              supplierId;
    private final Long              freightTemplateId;
    private final String            h5Detail;
    private final String            deliveryRegion;
    private final Long              weight;
    private final Long              updatedBy;
}
