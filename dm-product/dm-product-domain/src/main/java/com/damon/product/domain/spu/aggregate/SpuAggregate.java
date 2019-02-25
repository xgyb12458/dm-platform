package com.damon.product.domain.spu.aggregate;

import com.damon.product.domain.sku.aggregate.SkuAggregate;
import com.damon.product.domain.spu.command.*;
import com.damon.product.domain.spu.event.*;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.SpuState;
import com.damon.product.shared.enums.VerifyState;
import com.damon.shared.enums.YesNoEnum;
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
    private Long                imageId;
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
    private SpuState            state;
    /**新品标识*/
    private YesNoEnum           newProduct;
    /**是否已删除*/
    private YesNoEnum           removed;
    /**推荐标识*/
    private YesNoEnum           recommended;
    /**售罄标识*/
    private YesNoEnum           soldOut;
    /**是否可退货*/
    private YesNoEnum           supportReturn;
    /**商品库存*/
    private Integer             inventory;
    /**安全库存预警值*/
    private Integer             safetyStock;
    /**型号*/
    private String              model;
    /**商品类型*/
    private ProductType         type;
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
        log.info("creating spu aggregate command, parameters: {}", command.toString());

        // 验证参数是否合法
//        if (!SpuAdapter.validate(command)) {
//            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST);
//        }

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
                .newProduct(command.getNewProduct())
                .state(command.getState())
                .removed(command.getRemoved())
                .recommended(command.getRecommended())
                .soldOut(command.getSoldOut())
                .soldVolume(0)
                .price(command.getPrice())
                .marketPrice(command.getMarketPrice())
                .inventory(command.getInventory())
                .safetyStock(command.getSafetyStock())
                .model(command.getModel())
                .type(command.getType())
                .description(command.getDescription())
                .supportReturn(command.getSupportReturn())
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
    @CommandHandler
    private void handle(UpdateSpuCommand command) {
        log.info("updating spu aggregate command, parameters: {}", command.toString());

        // 验证参数是否合法

        // 参数合法状态下启动创建事件
        apply(SpuUpdatedEvent.builder()
                .spuId(command.getSpuId())
                .name(command.getName())
                .subTitle(command.getSubTitle())
                .imageId(command.getImageId())
                .skus(command.getSkus())
                .albumImages(command.getAlbumImages())
                .newProduct(command.getNewProduct())
                .recommended(command.getRecommended())
                .soldOut(command.getSoldOut())
                .price(command.getPrice())
                .marketPrice(command.getMarketPrice())
                .inventory(command.getInventory())
                .safetyStock(command.getSafetyStock())
                .model(command.getModel())
                .type(command.getType())
                .description(command.getDescription())
                .supportReturn(command.getSupportReturn())
                .categoryId(command.getCategoryId())
                .brandId(command.getBrandId())
                .warehouseId(command.getWarehouseId())
                .supplierId(command.getSupplierId())
                .freightTemplateId(command.getFreightTemplateId())
                .h5Detail(command.getH5Detail())
                .deliveryRegion(command.getDeliveryRegion())
                .weight(command.getWeight())
                .updatedBy(command.getUpdatedBy())
                .updatedAt(Instant.now())
                .build()
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(RemoveSpuCommand command) {
        log.info("deleting spu aggregate command, parameters: {}", command.toString());

        if (YesNoEnum.N.equals(getRemoved())) {
            apply(new SpuRemovedEvent(
                    command.getSpuId(),
                    command.getUpdatedBy())
            );
        }
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(RecoverSpuCommand command) {
        log.info("recovering spu aggregate command, parameters: {}", command.toString());

        if (YesNoEnum.Y.equals(getRemoved())) {
            apply(new SpuRecoveredEvent(
                    command.getSpuId(),
                    command.getUpdatedBy())
            );
        }
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(ChangeSpuNewProductCommand command) {
        log.info("changing spu aggregate newProduct command, parameters: {}", command.toString());

        apply(new SpuNewProductChangedEvent(
                command.getSpuId(),
                YesNoEnum.Y.equals(getNewProduct()) ? YesNoEnum.N : YesNoEnum.Y,
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(ChangeSpuRecommendedCommand command) {
        log.info("changing spu aggregate recommended command, parameters: {}", command.toString());

        apply(new SpuRecommendedChangedEvent(
                command.getSpuId(),
                YesNoEnum.Y.equals(getRecommended()) ? YesNoEnum.N : YesNoEnum.Y,
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(ChangeSpuSoldOutCommand command) {
        log.info("changing spu aggregate soldOut command, parameters: {}", command.toString());

        apply(new SpuSoldOutChangedEvent(
                command.getSpuId(),
                YesNoEnum.Y.equals(getSoldOut()) ? YesNoEnum.N : YesNoEnum.Y,
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(ChangeSpuSupportReturnCommand command) {
        log.info("changing spu aggregate supportReturn command, parameters: {}", command.toString());

        apply(new SpuSupportReturnChangedEvent(
                command.getSpuId(),
                YesNoEnum.Y.equals(getSupportReturn()) ? YesNoEnum.N : YesNoEnum.Y,
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(ResetSpuVerificationCommand command) {
        log.info("resetting spu aggregate verifyState command, parameters: {}", command.toString());

        apply(new SpuVerificationResettedEvent(
                command.getSpuId(),
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(CommitSpuVerificationCommand command) {
        log.info("committing spu aggregate to verify command, parameters: {}", command.toString());

        apply(new SpuCommittedEvent(
                command.getSpuId(),
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(ApproveSpuCommand command) {
        log.info("approving spu aggregate command, parameters: {}", command.toString());

        apply(new SpuApprovedEvent(
                command.getSpuId(),
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(RejectSpuCommand command) {
        log.info("rejecting spu aggregate command, parameters: {}", command.toString());

        apply(new SpuRejectedEvent(
                command.getSpuId(),
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuCreatedEvent event) {
        log.info("creating spu aggregate event, parameters: {}", event.toString());

//        if (!SpuAdapter.validate(event)) {
//            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST);
//        }
        // 设置属性值
        setSpuId(event.getSpuId());
        setSpuCode(event.getSpuCode());
        setName(event.getName());
        setSubTitle(event.getSubTitle());
        setImageId(event.getImageId());
        setAlbumImages(event.getAlbumImages());
        setVerifyState(event.getVerifyState());
        setState(event.getState());
        setNewProduct(event.getNewProduct());
        setRemoved(event.getRemoved());
        setRecommended(event.getRecommended());
        setSoldOut(event.getSoldOut());
        setPrice(event.getPrice());
        setMarketPrice(event.getMarketPrice());
        setInventory(event.getInventory());
        setSafetyStock(event.getSafetyStock());
        setSoldVolume(event.getSoldVolume());
        setModel(event.getModel());
        setType(event.getType());
        setSupportReturn(event.getSupportReturn());
        setCategoryId(event.getCategoryId());
        setBrandId(event.getBrandId());
        setWarehouseId(event.getWarehouseId());
        setSupplierId(event.getSupplierId());
        setH5Detail(event.getH5Detail());
        setFreightTemplateId(event.getFreightTemplateId());
        setDeliveryRegion(event.getDeliveryRegion());
        setWeight(event.getWeight());
        setDescription(event.getDescription());
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


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuUpdatedEvent event) {
        log.info("updating spu aggregate event, parameters: {}", event.toString());

        // 更新属性值
        setSpuId(event.getSpuId());
        setName(event.getName());
        setSubTitle(event.getSubTitle());
        setImageId(event.getImageId());
        setAlbumImages(event.getAlbumImages());
        setNewProduct(event.getNewProduct());
        setRecommended(event.getRecommended());
        setDescription(event.getDescription());
        setSoldOut(event.getSoldOut());
        setInventory(event.getInventory());
        setSafetyStock(event.getSafetyStock());
        setPrice(event.getPrice());
        setMarketPrice(event.getMarketPrice());
        setSupportReturn(event.getSupportReturn());
        setModel(event.getModel());
        setType(event.getType());
        setFreightTemplateId(event.getFreightTemplateId());
        setCategoryId(event.getCategoryId());
        setBrandId(event.getBrandId());
        setDeliveryRegion(event.getDeliveryRegion());
        setWarehouseId(event.getWarehouseId());
        setSupplierId(event.getSupplierId());
        setH5Detail(event.getH5Detail());
        setWeight(event.getWeight());
        setUpdatedBy(event.getUpdatedBy());
        setUpdatedAt(event.getUpdatedAt());
        // TODO: 更新SKU属性
//        setSkus(event.getSkus());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuRemovedEvent event) {
        log.info("deleting spu aggregate event, parameters: {}", event.toString());

        // 更新属性值
        setRemoved(event.getState());
        updateStateChangedValue(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuRecoveredEvent event) {
        log.info("recovering spu aggregate event, parameters: {}", event.toString());

        // 更新属性值
        setRemoved(event.getState());
        updateStateChangedValue(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuNewProductChangedEvent event) {
        log.info("changing spu aggregate newProduct event, parameters: {}", event.toString());

        // 更新属性值
        setNewProduct(event.getState());
        updateStateChangedValue(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuRecommendedChangedEvent event) {
        log.info("changing spu aggregate recommended event, parameters: {}", event.toString());

        // 更新属性值
        setRecommended(event.getState());
        updateStateChangedValue(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuSoldOutChangedEvent event) {
        log.info("changing spu aggregate soldOut event, parameters: {}", event.toString());

        // 更新属性值
        setSoldOut(event.getState());
        updateStateChangedValue(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuSupportReturnChangedEvent event) {
        log.info("changing spu aggregate supportReturn event, parameters: {}", event.toString());

        // 更新属性值
        setSupportReturn(event.getState());
        updateStateChangedValue(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuVerificationResettedEvent event) {
        log.info("resetting spu aggregate verifyState to DRAFTING event, parameters: {}", event.toString());

        // 更新审核状态
        changeVerifyState(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuCommittedEvent event) {
        log.info("committing spu aggregate to verify[AUDITING] event, parameters: {}", event.toString());

        // 更新审核状态
        changeVerifyState(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuApprovedEvent event) {
        log.info("approving spu aggregate event, parameters: {}", event.toString());

        // 更新审核状态
        changeVerifyState(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(SpuRejectedEvent event) {
        log.info("rejecting spu aggregate event, parameters: {}", event.toString());

        // 更新审核状态
        changeVerifyState(event);
    }

    /**
     * 更新审核状态值
     * @param event 商品审核事件
     */
    private void changeVerifyState(SpuVerifiedEvent event) {
        setVerifyState(event.getState());
        setUpdatedBy(event.getUpdatedBy());
        setUpdatedAt(event.getUpdatedAt());
    }

    /**
     * 更新状态变更后的属性值
     * @param event 状态变更事件
     */
    private void updateStateChangedValue(SpuStateChangedEvent event) {
        setUpdatedBy(event.getUpdatedBy());
        setUpdatedAt(event.getUpdatedAt());
    }
}
