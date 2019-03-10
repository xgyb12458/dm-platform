package com.damon.product.core.query.repository.brand;

import com.damon.product.domain.brand.entity.BrandEntry;
import com.damon.product.domain.brand.entity.BrandRepository;
import com.damon.product.domain.brand.entity.QBrandEntry;
import com.damon.product.domain.brand.event.BrandCreatedEvent;
import com.damon.product.domain.brand.event.BrandRecoveredEvent;
import com.damon.product.domain.brand.event.BrandRemovedEvent;
import com.damon.product.domain.brand.event.BrandUpdatedEvent;
import com.damon.shared.common.Constants;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 品牌数据事件侦听器
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月10日 15:54
 */
@Slf4j
@Component
public class BrandEventListener {

    private final JPAQueryFactory jpaQueryFactory;
    private final BrandRepository brandRepository;
    private final QBrandEntry qBrandEntry;


    public BrandEventListener(EntityManagerProvider managerProvider,
                              BrandRepository brandRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.brandRepository = brandRepository;
        this.qBrandEntry = QBrandEntry.brandEntry;
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(BrandCreatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling BrandCreatedEvent persistence process, parameters：{}", event.toString());

        BrandEntry brandEntry = BrandEntry.builder()
                .brandId(event.getBrandId().getValue())
                .name(event.getName())
                .code(event.getCode())
                .logo(event.getLogo())
                .sort(event.getSort())
                .bigImage(event.getBigImage())
                .display(event.getDisplay().name())
                .removed(event.getRemoved().name())
                .factoryState(event.getFactoryState().name())
                .firstLetter(event.getFirstLetter())
                .homepage(event.getHomepage())
                .description(event.getDescription())
                .createdBy(event.getCreatedBy())
                .createdAt(new Timestamp(event.getCreatedAt().toEpochMilli()))
                .build();

        brandRepository.saveAndFlush(brandEntry);
        log.info(Constants.PREFIX_PRODUCT + "========>>Brand aggregate[Id:{}, name:'{}'] created by User[Id:{}] at {} is successfully stored-[DB].",
                event.getBrandId().getValue(), event.getName(), event.getCreatedBy(), event.getCreatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(BrandUpdatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling BrandUpdatedEvent persistence process, parameters：{}", event.toString());

        jpaQueryFactory.update(qBrandEntry)
                .set(qBrandEntry.name, event.getName())
                .set(qBrandEntry.code, event.getCode())
                .set(qBrandEntry.logo, event.getLogo())
                .set(qBrandEntry.sort, event.getSort())
                .set(qBrandEntry.firstLetter, event.getFirstLetter())
                .set(qBrandEntry.bigImage, event.getBigImage())
                .set(qBrandEntry.display, event.getDisplay().name())
                .set(qBrandEntry.factoryState, event.getFactoryState().name())
                .set(qBrandEntry.homepage, event.getHomepage())
                .set(qBrandEntry.description, event.getDescription())
                .set(qBrandEntry.updatedBy, event.getUpdatedBy())
                .set(qBrandEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .where(qBrandEntry.brandId.eq(event.getBrandId().getValue()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>Brand aggregate[Id:{}] is updated by User[Id:{}] at {}-[DB].",
                event.getBrandId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(BrandRemovedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling BrandRemovedEvent persistence process, parameters：{}", event.toString());

        jpaQueryFactory.update(qBrandEntry)
                .set(qBrandEntry.removed, event.getState().name())
                .set(qBrandEntry.updatedBy, event.getUpdatedBy())
                .set(qBrandEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>Brand aggregate[Id:{}] is removed by User[Id:{}] at {}-[DB].",
                event.getBrandId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(BrandRecoveredEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling BrandRecoveredEvent persistence process, parameters：{}", event.toString());

        jpaQueryFactory.update(qBrandEntry)
                .set(qBrandEntry.updatedBy, event.getUpdatedBy())
                .set(qBrandEntry.removed, event.getState().name())
                .set(qBrandEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>Brand aggregate[Id:{}] is recovered by User[Id:{}] at {}-[DB].",
                event.getBrandId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
