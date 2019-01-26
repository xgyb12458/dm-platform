package com.damon.product.domain.spu.aggregate;

import com.damon.product.domain.sku.aggregate.SkuAggregate;
import com.damon.product.domain.sku.aggregate.SkuId;
import com.damon.product.domain.sku.command.CreateSkuCommand;
import com.damon.product.domain.spu.command.CreateSpuCommand;
import com.damon.product.domain.spu.event.ProductCreatedEvent;
import com.damon.product.shared.enums.ProductState;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.ReviewState;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.enums.YesNoEnum;
import com.damon.shared.exception.BusinessException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * SPU主商品
 * @author Damon S.
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class SpuAggregate {

    @AggregateIdentifier
    private SpuId       spuId;
    private String      spuCode;
    private String      name;
    @AggregateMember
    private List<SkuAggregate> skus;
    private List<Long>  images;
    private String      desc;
    private Long        price;
    private ReviewState reviewState;
    private ProductState productState;
    private YesNoEnum   removed;
    private Integer     inventory;
    private String      model;
    private ProductType type;
    private Boolean     canReturn;
    private Long        categoryId;
    private Long        brandId;
    private Long        warehouseId;
    private Long        supplierId;
    private String      h5Detail;
    private Integer     soldVolume;
    private String      deliveryRegion;
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
    public SpuAggregate(CreateSpuCommand command) {
        // 验证参数是否合法
        if (!SpuAdapter.validate(command)) {
            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST);
        }
        // 参数合法状态下启动创建事件
        apply(ProductCreatedEvent.builder()
                .spuId(command.getSpuId())
                .spuCode(command.getSpuCode())
                .name(command.getName())
                .skus(command.getSkus())
                .images(command.getImages())
                .reviewState(command.getReviewState())
                .productState(command.getProductState())
                .removed(command.getRemoved())
                .desc(command.getDesc())
                .price(command.getPrice())
                .inventory(command.getInventory())
                .model(command.getModel())
                .type(command.getType())
                .canReturn(command.getCanReturn())
                .categoryId(command.getCategoryId())
                .brandId(command.getBrandId())
                .warehouseId(command.getWarehouseId())
                .supplierId(command.getSupplierId())
                .h5Detail(command.getH5Detail())
                .deliveryRegion(command.getDeliveryRegion())
                .length(command.getLength())
                .width(command.getWidth())
                .height(command.getHeight())
                .weight(command.getWeight())
                .boxNum(command.getBoxNum())
                .createdBy(command.getCreatedBy())
                .build()
        );
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        if (!SpuAdapter.validate(event)) {
            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST);
        }
        setSpuId(event.getSpuId());
        setSpuCode(event.getSpuCode());
        setName(event.getName());
        setImages(event.getImages());
        setReviewState(event.getReviewState());
        setProductState(event.getProductState());
        setRemoved(event.getRemoved());
        setDesc(event.getDesc());
        setPrice(event.getPrice());
        setInventory(event.getInventory());
        setModel(event.getModel());
        setType(event.getType());
        setCanReturn(event.getCanReturn());
        setCategoryId(event.getCategoryId());
        setBrandId(event.getBrandId());
        setWarehouseId(event.getWarehouseId());
        setSupplierId(event.getSupplierId());
        setH5Detail(event.getH5Detail());
        setDeliveryRegion(event.getDeliveryRegion());
        setLength(event.getLength());
        setWidth(event.getWidth());
        setHeight(event.getHeight());
        setWeight(event.getWeight());
        setBoxNum(event.getBoxNum());
        setCreatedBy(event.getCreatedBy());
//        setSkus(event.getSkus().stream().map(
//                sku -> {
//                    CreateSkuCommand.builder()
//                            .skuId(new SkuId())
//                            .specIds(sku.getSpecIds())
//                            .skuCode(sku.getSkuCode())
//                            .name(sku.getName())
//                            .images(sku.getImages())
//                            .inventory(sku.getInventory())
//                            .secureInventory(sku.getSecureInventory())
//                            .price(sku.getPrice())
//                            .reduction(sku.getReduction())
//                            .promoteFee(sku.getPromoteFee())
//                            .serviceFee(sku.getServiceFee())
//                            .exchangePrice(sku.getExchangePrice())
//                            .exchangePoint(sku.getExchangePoint())
//                            .netWorth(sku.getNetWorth())
//                            .barCode(sku.getBarCode())
//                            .createdBy(event.getCreatedBy())
//                            .build();
//                }
//        ));
    }
}
