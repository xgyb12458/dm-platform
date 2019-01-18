package com.damon.product.domain.spu.aggregate;

import com.damon.product.domain.spu.command.CreateProductCommand;
import com.damon.product.shared.enums.ProductModel;
import com.damon.product.shared.enums.ProductState;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.ReviewState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

/**
 * SPU主商品
 * @author Damon S.
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@ToString
@Aggregate
@NoArgsConstructor
public class SpuAggregate {

    @AggregateIdentifier
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

    @CommandHandler
    public SpuAggregate(CreateProductCommand command) {

    }
}
