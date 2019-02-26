package com.damon.product.core.query.repository.spu;

import com.damon.product.domain.spu.entity.QSpuEntry;
import com.damon.product.domain.spu.entity.SpuEntry;
import com.damon.product.domain.spu.entity.SpuRepository;
import com.damon.product.domain.spu.event.*;
import com.damon.shared.common.Constants;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * SPU事件侦听器
 * @author Damon S.
 */
@Slf4j
@Component
public class SpuEventListener {

    private final JPAQueryFactory jpaQueryFactory;
    private final QSpuEntry qSpuEntry;
    private final SpuRepository spuRepository;

    private final static Integer NUM_ONE = 1;

    public SpuEventListener(EntityManagerProvider managerProvider,
                            SpuRepository spuRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qSpuEntry = QSpuEntry.spuEntry;
        this.spuRepository = spuRepository;
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuCreatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuCreatedEvent persistence process, parameters：{}", event.toString());

        SpuEntry spuEntry = SpuEntry.builder()
                .spuId(event.getSpuId().getValue())
                .spuCode(event.getSpuCode())
                .name(event.getName())
                .subTitle(event.getSubTitle())
                .imageId(event.getImageId())
                .albumImages(collection2Plain(event.getAlbumImages()))
                .description(event.getDescription())
                .price(event.getPrice())
                .marketPrice(event.getMarketPrice())
                .verifyState(event.getVerifyState().name())
                .state(event.getState().name())
                .soldOut(event.getSoldOut().name())
                .newProduct(event.getNewProduct().name())
                .recommended(event.getRecommended().name())
                .removed(event.getRemoved().name())
                .supportReturn(event.getSupportReturn().name())
                .weight(event.getWeight())
                .soldVolume(event.getSoldVolume())
                .inventory(event.getInventory())
                .safetyStock(event.getSafetyStock())
                .description(event.getDescription())
                .model(event.getModel())
                .type(event.getType().name())
                .freightTemplateId(event.getFreightTemplateId())
                .categoryId(event.getCategoryId())
                .brandId(event.getBrandId())
                .warehouseId(event.getWarehouseId())
                .supplierId(event.getSupplierId())
                .h5Detail(event.getH5Detail())
                .deliveryRegion(event.getDeliveryRegion())
                .createdBy(event.getCreatedBy())
                .createdAt(new Timestamp(event.getCreatedAt().toEpochMilli()))
                .build();

        spuRepository.saveAndFlush(spuEntry);
        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}, name:'{}'] created by User[Id:{}] at {} is successfully stored-[DB].",
                event.getSpuId().getValue(), event.getName(), event.getCreatedBy(), event.getCreatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuUpdatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuUpdatedEvent persistence process, parameters：{}", event.toString());

        jpaQueryFactory.update(qSpuEntry)
                .set(qSpuEntry.name, event.getName())
                .set(qSpuEntry.subTitle, event.getSubTitle())
                .set(qSpuEntry.imageId, event.getImageId())
                .set(qSpuEntry.albumImages, collection2Plain(event.getAlbumImages()))
                .set(qSpuEntry.price, event.getPrice())
                .set(qSpuEntry.marketPrice, event.getMarketPrice())
                .set(qSpuEntry.newProduct, event.getNewProduct().name())
                .set(qSpuEntry.recommended, event.getRecommended().name())
                .set(qSpuEntry.soldOut, event.getSoldOut().name())
                .set(qSpuEntry.inventory, event.getInventory())
                .set(qSpuEntry.safetyStock, event.getSafetyStock())
                .set(qSpuEntry.model, event.getModel())
                .set(qSpuEntry.type, event.getType().name())
                .set(qSpuEntry.supportReturn, event.getSupportReturn().name())
                .set(qSpuEntry.categoryId, event.getCategoryId())
                .set(qSpuEntry.brandId, event.getBrandId())
                .set(qSpuEntry.warehouseId, event.getWarehouseId())
                .set(qSpuEntry.supplierId, event.getSupplierId())
                .set(qSpuEntry.freightTemplateId, event.getFreightTemplateId())
                .set(qSpuEntry.h5Detail, event.getH5Detail())
                .set(qSpuEntry.deliveryRegion, event.getDeliveryRegion())
                .set(qSpuEntry.weight, event.getWeight())
                .set(qSpuEntry.description, event.getDescription())
                .set(qSpuEntry.updatedBy, event.getUpdatedBy())
                .set(qSpuEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .where(qSpuEntry.spuId.eq(event.getSpuId().getValue()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] is updated by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuRemovedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuRemovedEvent process, parameters：{}", event.toString());

        this.handleSpuStateChangeEvent(event, qSpuEntry.removed);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] has bean removed by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuRecoveredEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuRecoveredEvent process, parameters：{}", event.toString());

        this.handleSpuStateChangeEvent(event, qSpuEntry.removed);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] has bean recovered by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuNewProductChangedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuNewProductChangedEvent process, parameters：{}", event.toString());

        this.handleSpuStateChangeEvent(event, qSpuEntry.newProduct);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] newProduct has bean updated by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuRecommendedChangedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuRecommendedChangedEvent process, parameters：{}", event.toString());

        this.handleSpuStateChangeEvent(event, qSpuEntry.recommended);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] recommended has bean updated by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuSoldOutChangedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuSoldOutChangedEvent process, parameters：{}", event.toString());

        this.handleSpuStateChangeEvent(event, qSpuEntry.soldOut);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] soldOut has bean updated by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuSupportReturnChangedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuSupportReturnChangedEvent process, parameters：{}", event.toString());

        this.handleSpuStateChangeEvent(event, qSpuEntry.supportReturn);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] supportReturn has bean updated by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuVerificationResettedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuVerificationResettedEvent process, parameters：{}", event.toString());

        this.handleSpuVerifyEvent(event);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] has bean reset to verify by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuCommittedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuCommittedEvent process, parameters：{}", event.toString());

        this.handleSpuVerifyEvent(event);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] has bean committed to verify by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuApprovedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuApprovedEvent process, parameters：{}", event.toString());

        this.handleSpuVerifyEvent(event);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] has bean approved by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SpuRejectedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SpuRejectedEvent process, parameters：{}", event.toString());

        this.handleSpuVerifyEvent(event);

        log.info(Constants.PREFIX_PRODUCT + "========>>Spu aggregate[Id:{}] has bean rejected by User[Id:{}] at {}-[DB].",
                event.getSpuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    /**
     * 处理商品状态变更事件
     * @param event 变更事件
     */
    private void handleSpuStateChangeEvent(SpuStateChangedEvent event, StringPath updatedField) {
        jpaQueryFactory.update(qSpuEntry)
                .set(updatedField, event.getState().name())
                .set(qSpuEntry.updatedBy, event.getUpdatedBy())
                .set(qSpuEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .where(qSpuEntry.spuId.eq(event.getSpuId().getValue()))
                .execute();
    }


    /**
     * 处理商品审核状态
     * @param event 审核事件
     */
    private void handleSpuVerifyEvent(SpuVerifiedEvent event) {
        jpaQueryFactory.update(qSpuEntry)
                .set(qSpuEntry.verifyState, event.getState().name())
                .set(qSpuEntry.updatedBy, event.getUpdatedBy())
                .set(qSpuEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .where(qSpuEntry.spuId.eq(event.getSpuId().getValue()))
                .execute();
    }


    /**
     * 将Long集合转换为逗号分隔的字符串进行存储
     * @param collection Long集合
     * @return 逗号分隔的字符串或者为空
     */
    private String collection2Plain(Collection<Long> collection) {
        StringBuilder builder = new StringBuilder();

        if (!CollectionUtils.isEmpty(collection)) {
            collection.forEach(imageId ->
                    builder.append(Constants.STR_COMMA).append(imageId)
            );
        }
        if (builder.length() <= NUM_ONE) {
            return Constants.STR_EMPTY;
        }
        return builder.substring(NUM_ONE);
    }
}
