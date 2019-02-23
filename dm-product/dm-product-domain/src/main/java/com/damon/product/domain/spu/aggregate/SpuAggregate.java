package com.damon.product.domain.spu.aggregate;

import com.damon.product.domain.sku.aggregate.SkuAggregate;
import com.damon.product.domain.spu.command.CreateSpuCommand;
import com.damon.product.domain.spu.event.SpuCreatedEvent;
import com.damon.product.shared.enums.SpuState;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.VerifyState;
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
    private SpuId               spuId;
    @AggregateMember
    private List<SkuAggregate>  skus;
    /**商品编码*/
    private String              spuCode;
    /**商品名称*/
    private String              name;
    /**商品副标题*/
    private String              subTitle;
    /**主图*/
    private String              imageId;
    /**商品图片集合*/
    private List<Long>          albumImages;
    /**商品描述*/
    private String              description;
    /**商品价格*/
    private Long                price;
    /**商品市场价*/
    private Long                marketPrice;
    /**审核状态*/
    private VerifyState         verifyState;
    /**商品状态：草稿，待发布，已上架，已下架*/
    private SpuState            spuState;
    /**新品标识*/
    private YesNoEnum           newState;
    /**是否已删除*/
    private YesNoEnum           removed;
    /**推荐标识*/
    private YesNoEnum           recommended;
    /**售罄标识*/
    private YesNoEnum           soldOut;
    /**商品库存*/
    private Integer             inventory;
    /**库存预警值*/
    private Integer             lowInventoryLimit;
    /**型号*/
    private String              model;
    /**商品类型*/
    private ProductType         type;
    /**是否可退货*/
    private YesNoEnum           canReturn;
    /**商品类别*/
    private Long                categoryId;
    /**商品品牌*/
    private Long                brandId;
    /**发货仓库*/
    private Long                warehouseId;
    /**供应商*/
    private Long                supplierId;
    /**运费模板*/
    private Long                freightTemplateId;
    /**商品详情*/
    private String              h5Detail;
    /**销量*/
    private Integer             soldVolume;
    /**配送区域*/
    private String              deliveryRegion;
    /**商品重量，单位：毫克*/
    private Long                weight;
    private Long                createdBy;
    private Long                updatedBy;
    private Instant             createdAt;
    private Instant             updatedAt;


    @CommandHandler
    public SpuAggregate(CreateSpuCommand command) {
        // 验证参数是否合法
        if (!SpuAdapter.validate(command)) {
            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST);
        }

        /*
        try {
            command.getSkus().stream().map(
                    sku -> AggregateLifecycle.createNew(SkuAggregate.class, () -> new SkuAggregate())
            ).collect(Collectors.toList());
        } catch (Exception e) {

        }
        */

        // 参数合法状态下启动创建事件
        apply(SpuCreatedEvent.builder()
                .spuId(command.getSpuId())
                .spuCode(command.getSpuCode())
                .name(command.getName())
                .subTitle(command.getSubTitle())
                .imageId(command.getImageId())
                .skus(command.getSkus())
                .albumImages(command.getAlbumImages())
                .verifyState(command.getVerifyState())
                .newState(command.getNewState())
                .spuState(command.getSpuState())
                .removed(command.getRemoved())
                .recommended(command.getRecommended())
                .soldOut(command.getSoldOut())
                .price(command.getPrice())
                .marketPrice(command.getMarketPrice())
                .inventory(command.getInventory())
                .lowInventoryLimit(command.getLowInventoryLimit())
                .model(command.getModel())
                .soldVolume(command.getSoldVolume())
                .type(command.getType())
                .description(command.getDescription())
                .canReturn(command.getCanReturn())
                .categoryId(command.getCategoryId())
                .brandId(command.getBrandId())
                .warehouseId(command.getWarehouseId())
                .supplierId(command.getSupplierId())
                .freightTemplateId(command.getFreightTemplateId())
                .h5Detail(command.getH5Detail())
                .deliveryRegion(command.getDeliveryRegion())
                .weight(command.getWeight())
                .createdBy(command.getCreatedBy())
                .createdAt(Instant.now())
                .build()
        );
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    public void on(SpuCreatedEvent event) {
        if (!SpuAdapter.validate(event)) {
            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST);
        }
        setSpuId(event.getSpuId());
        setSpuCode(event.getSpuCode());
        setName(event.getName());
        setSubTitle(event.getSubTitle());
        setImageId(event.getImageId());
        setAlbumImages(event.getAlbumImages());
        setVerifyState(event.getVerifyState());
        setSpuState(event.getSpuState());
        setNewState(event.getNewState());
        setRemoved(event.getRemoved());
        setRecommended(event.getRecommended());
        setSoldOut(event.getSoldOut());
        setDescription(event.getDescription());
        setPrice(event.getPrice());
        setMarketPrice(event.getMarketPrice());
        setInventory(event.getInventory());
        setLowInventoryLimit(event.getLowInventoryLimit());
        setSoldVolume(event.getSoldVolume());
        setModel(event.getModel());
        setType(event.getType());
        setCanReturn(event.getCanReturn());
        setCategoryId(event.getCategoryId());
        setBrandId(event.getBrandId());
        setWarehouseId(event.getWarehouseId());
        setSupplierId(event.getSupplierId());
        setH5Detail(event.getH5Detail());
        setFreightTemplateId(event.getFreightTemplateId());
        setDeliveryRegion(event.getDeliveryRegion());
        setWeight(event.getWeight());
        setCreatedBy(event.getCreatedBy());
        setCreatedAt(event.getCreatedAt());

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
