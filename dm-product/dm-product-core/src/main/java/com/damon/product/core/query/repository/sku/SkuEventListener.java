package com.damon.product.core.query.repository.sku;

import com.damon.product.domain.sku.entity.*;
import com.damon.product.domain.sku.event.SkuCreatedEvent;
import com.damon.product.domain.sku.event.SkuUpdatedEvent;
import com.damon.product.shared.utils.CollectionUtil;
import com.damon.shared.common.Constants;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * SKU事件侦听器
 * @author Damon S.
 */
@Slf4j
@Component
public class SkuEventListener {

    private final JPAQueryFactory jpaQueryFactory;
    private final SkuRepository skuRepository;
    private final QSkuEntry qSkuEntry;
    private final SkuSpecRelateRepository skuSpecRelateRepository;
    private final SpecificationRepository specificationRepository;


    public SkuEventListener(EntityManagerProvider managerProvider,
                            SkuRepository skuRepository,
                            SkuSpecRelateRepository skuSpecRelateRepository,
                            SpecificationRepository specificationRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.skuRepository = skuRepository;
        this.qSkuEntry = QSkuEntry.skuEntry;
        this.skuSpecRelateRepository = skuSpecRelateRepository;
        this.specificationRepository = specificationRepository;
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SkuCreatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SkuCreatedEvent persistence process, parameters：{}", event.toString());

        SkuEntry skuEntry = SkuEntry.builder()
                .skuId(event.getSkuId().getValue())
                .name(event.getName())
                .skuCode(event.getSkuCode())
                .spuId(event.getSpuId())
//                .specIds(CollectionUtil.collection2Plain(event.getSpecIds()))
                .imageIds(CollectionUtil.collection2Plain(event.getImageIds()))
                .inventory(event.getInventory())
                .safetyStock(event.getSafetyStock())
                .price(event.getPrice())
                .reduction(event.getReduction())
                .promoteFee(event.getPromoteFee())
                .serviceFee(event.getServiceFee())
                .exchangePrice(event.getExchangePrice())
                .exchangePoint(event.getExchangePoint())
                .netWorth(event.getNetWorth())
                .barCode(event.getBarCode())
                .createdBy(event.getCreatedBy())
                .createdAt(new Timestamp(event.getCreatedAt().toEpochMilli()))
                .build();

        SkuSpecRelateEntry skuSpecRelateEntry = SkuSpecRelateEntry.builder()
                .skuId(event.getSkuId().getValue())
//                .sort(event.get)
//                .specId(event.get)
                .build();

        skuSpecRelateRepository.saveAndFlush(skuSpecRelateEntry);
        skuRepository.saveAndFlush(skuEntry);
        log.info(Constants.PREFIX_PRODUCT + "========>>Sku aggregate[Id:{}, name:'{}'] created by User[Id:{}] at {} is successfully stored-[DB].",
                event.getSkuId().getValue(), event.getName(), event.getCreatedBy(), event.getCreatedAt());
    }



    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(SkuUpdatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling SkuUpdatedEvent persistence process, parameters：{}", event.toString());

        jpaQueryFactory.update(qSkuEntry)
                .set(qSkuEntry.name, event.getName())
//                .set(qSkuEntry.imageIds, event.getImageIds())
                .set(qSkuEntry.inventory, event.getInventory())
                .set(qSkuEntry.safetyStock, event.getSafetyStock())
                .set(qSkuEntry.price, event.getPrice())
                .set(qSkuEntry.reduction, event.getReduction())
                .set(qSkuEntry.promoteFee, event.getPromoteFee())
                .set(qSkuEntry.serviceFee, event.getServiceFee())
                .set(qSkuEntry.exchangePoint, event.getExchangePoint())
                .set(qSkuEntry.exchangePrice, event.getExchangePrice())
                .set(qSkuEntry.netWorth, event.getNetWorth())
                .set(qSkuEntry.barCode, event.getBarCode())
                .set(qSkuEntry.updatedBy, event.getUpdatedBy())
                .set(qSkuEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .where(qSkuEntry.skuId.eq(event.getSkuId().getValue()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>Sku aggregate[Id:{}] is updated by User[Id:{}] at {}-[DB].",
                event.getSkuId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
