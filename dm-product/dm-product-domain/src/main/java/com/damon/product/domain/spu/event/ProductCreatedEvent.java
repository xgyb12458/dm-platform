package com.damon.product.domain.spu.event;

import com.damon.product.domain.sku.aggregate.ProductSku;
import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.shared.enums.SpuState;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.VerifyState;
import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.List;

/***
 * 交易创建完成事件
 * @author Damon S.
 */
@Value
@Builder
public class ProductCreatedEvent {
    private final SpuId             spuId;
    private final String            spuCode;
    private final String            name;
    private final String            subTitle;
    private final String            imageId;
    private final List<Long>        albumImages;
    private final List<ProductSku>  skus;
    private final String            description;
    private final Long              price;
    private final Long              marketPrice;
    private final VerifyState       verifyState;
    private final YesNoEnum         newState;
    private final SpuState          spuState;
    private final YesNoEnum         removed;
    private final YesNoEnum         recommended;
    private final YesNoEnum         soldOut;
    private final Integer           inventory;
    private final Integer           lowInventoryLimit;
    private final Integer           soldVolume;
    private final String            model;
    private final ProductType       type;
    private final YesNoEnum         canReturn;
    private final Long              categoryId;
    private final Long              brandId;
    private final Long              warehouseId;
    private final Long              supplierId;
    private final Long              freightTemplateId;
    private final String            h5Detail;
    private final String            deliveryRegion;
    private final Long              weight;
    private final Long              createdBy;
    private final Instant           createdAt;
}
