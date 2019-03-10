package com.damon.product.core.query.repository.category;

import com.damon.product.domain.category.entity.CategoryEntry;
import com.damon.product.domain.category.entity.CategoryRepository;
import com.damon.product.domain.category.entity.QCategoryEntry;
import com.damon.product.domain.category.event.CategoryCreatedEvent;
import com.damon.product.domain.category.event.CategoryRecoveredEvent;
import com.damon.product.domain.category.event.CategoryRemovedEvent;
import com.damon.product.domain.category.event.CategoryUpdatedEvent;
import com.damon.shared.common.Constants;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * CATEGORY事件侦听器
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月03日 22:55
 */
@Slf4j
@Component
public class CategoryEventListener {

    private final JPAQueryFactory jpaQueryFactory;
    private final CategoryRepository categoryRepository;
    private final QCategoryEntry qCategoryEntry;


    public CategoryEventListener(EntityManagerProvider managerProvider,
                                 CategoryRepository categoryRepository) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.categoryRepository = categoryRepository;
        this.qCategoryEntry = QCategoryEntry.categoryEntry;
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(CategoryCreatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling CategoryCreatedEvent persistence process, parameters：{}", event.toString());

        CategoryEntry categoryEntry = CategoryEntry.builder()
                .categoryId(event.getCategoryId().getValue())
                .name(event.getName())
                .icon(event.getIcon())
                .level(event.getLevel())
                .parentId(event.getParentId())
                .sort(event.getSort())
                .spuCount(event.getSpuCount())
                .removed(event.getRemoved().name())
                .spuUnit(event.getSpuUnit())
                .showState(event.getShowState().name())
                .navState(event.getNavState().name())
                .keywords(event.getKeywords())
                .description(event.getDescription())
                .createdBy(event.getCreatedBy())
                .createdAt(new Timestamp(event.getCreatedAt().toEpochMilli()))
                .build();

        categoryRepository.saveAndFlush(categoryEntry);
        log.info(Constants.PREFIX_PRODUCT + "========>>Category aggregate[Id:{}, name:'{}'] created by User[Id:{}] at {} is successfully stored-[DB].",
                event.getCategoryId().getValue(), event.getName(), event.getCreatedBy(), event.getCreatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(CategoryUpdatedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling CategoryUpdatedEvent persistence process, parameters：{}", event.toString());

        jpaQueryFactory.update(qCategoryEntry)
                .set(qCategoryEntry.name, event.getName())
                .set(qCategoryEntry.icon, event.getIcon())
                .set(qCategoryEntry.level, event.getLevel())
                .set(qCategoryEntry.parentId, event.getParentId())
                .set(qCategoryEntry.sort, event.getSort())
                .set(qCategoryEntry.spuCount, event.getSpuCount())
                .set(qCategoryEntry.spuUnit, event.getSpuUnit())
                .set(qCategoryEntry.showState, event.getShowState().name())
                .set(qCategoryEntry.navState, event.getNavState().name())
                .set(qCategoryEntry.keywords, event.getKeywords())
                .set(qCategoryEntry.description, event.getDescription())
                .set(qCategoryEntry.updatedBy, event.getUpdatedBy())
                .set(qCategoryEntry.updatedAt, new java.sql.Timestamp(event.getUpdatedAt().toEpochMilli()))
                .where(qCategoryEntry.categoryId.eq(event.getCategoryId().getValue()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>Category aggregate[Id:{}] is updated by User[Id:{}] at {}-[DB].",
                event.getCategoryId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(CategoryRemovedEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling CategoryRemovedEvent persistence process, parameters：{}", event.toString());

        jpaQueryFactory.update(qCategoryEntry)
                .set(qCategoryEntry.removed, event.getState().name())
                .set(qCategoryEntry.updatedBy, event.getUpdatedBy())
                .set(qCategoryEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>Category aggregate[Id:{}] is removed by User[Id:{}] at {}-[DB].",
                event.getCategoryId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventHandler
    private void on(CategoryRecoveredEvent event) {
        log.trace(Constants.PREFIX_PRODUCT + "========>>handling CategoryRecoveredEvent persistence process, parameters：{}", event.toString());

        jpaQueryFactory.update(qCategoryEntry)
                .set(qCategoryEntry.updatedBy, event.getUpdatedBy())
                .set(qCategoryEntry.removed, event.getState().name())
                .set(qCategoryEntry.updatedAt, new Timestamp(event.getUpdatedAt().toEpochMilli()))
                .execute();

        log.info(Constants.PREFIX_PRODUCT + "========>>Category aggregate[Id:{}] is recovered by User[Id:{}] at {}-[DB].",
                event.getCategoryId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
