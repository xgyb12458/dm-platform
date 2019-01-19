package com.damon.product.domain.spu.command;

import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.shared.enums.ProductModel;
import com.damon.product.shared.enums.ProductState;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.ReviewState;
import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Damon S.
 */
@Value
@Builder
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private final SpuId       spuId;
    private final String      spuCode;
    private final String      name;
    private final String      image;
    private final String      desc;
    private final Long        price;
    private final ReviewState reviewState;
    private final ProductState productState;
    private final YesNoEnum   removed;
    private final Integer     inventory;
    private final ProductModel model;
    private final ProductType type;
    private final Boolean     canReturn;
    private final Long        categoryId;
    private final Long        brandId;
    private final Long        warehouseId;
    private final Long        supplierId;
    private final String      h5Detail;
    private final Integer     soldVolume;
    private final String      deliverRegion;
    private final Long        length;
    private final Long        width;
    private final Long        height;
    private final Long        weight;
    private final Long        boxNum;
    private final Long        createdBy;
    private final Long        updatedBy;
}
