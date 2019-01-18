package com.damon.product.domain.spu.command;

import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.shared.enums.ProductModel;
import com.damon.product.shared.enums.ProductState;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.ReviewState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;

/**
 * @author Damon S.
 */
@Getter
@Setter(AccessLevel.MODULE)
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private SpuId       spuId;
    private String      spuCode;
    private String      name;
    private String      image;
    private String      desc;
    private Long        price;
    private ReviewState reviewState;
    private ProductState productState;
    private Integer     removed;
    private Integer     inventory;
    private ProductModel model;
    private ProductType type;
    private Boolean     canReturn;
    private Long        categoryId;
    private Long        brandId;
    private Long        warehouseId;
    private Long        supplierId;
    private String      h5Detail;
    private Integer     saleVolume;
    private String      deliverRegion;
    private Long        length;
    private Long        width;
    private Long        height;
    private Long        weight;
    private Long        boxNum;
    private Long        createdBy;
    private Long        updatedBy;
    private Instant     createdAt;
    private Instant     updatedAt;
}
