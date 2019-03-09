package com.damon.product.domain.category.aggregate;

import com.damon.product.domain.category.command.CreateCategoryCommand;
import com.damon.product.domain.category.command.RecoverCategoryCommand;
import com.damon.product.domain.category.command.RemoveCategoryCommand;
import com.damon.product.domain.category.command.UpdateCategoryCommand;
import com.damon.product.domain.category.event.*;
import com.damon.shared.common.Constants;
import com.damon.shared.enums.YesNoEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 10:14
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class CategoryAggregate {

    @AggregateIdentifier
    private CategoryId      categoryId;
    private String          name;
    private Integer         level;
    private String          icon;
    private Integer         spuCount;
    private String          spuUnit;
    private YesNoEnum       navState;
    private YesNoEnum       showState;
    private Integer         sort;
    private YesNoEnum       removed;
    private String          keywords;
    private Long            parentId;
    private String          description;
    private Long            createdBy;
    private Instant         createdAt;
    private Long            updatedBy;
    private Instant         updatedAt;


    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>creating category aggregate command, parameters: {}", command.toString());

        apply(CategoryCreatedEvent.builder()
                .categoryId(command.getCategoryId())
                .name(command.getName())
                .icon(command.getIcon())
                .level(command.getLevel())
                .sort(command.getSort())
                .spuCount(command.getSpuCount())
                .spuUnit(command.getSpuUnit())
                .showState(command.getShowState())
                .navState(command.getNavState())
                .removed(YesNoEnum.N)
                .keywords(command.getKeywords())
                .parentId(command.getParentId())
                .description(command.getDescription())
                .createdBy(command.getCreatedBy())
                .createdAt(Instant.now())
                .build()
        );
    }

    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(UpdateCategoryCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>updating category aggregate command, parameters: {}", command.toString());

        apply(CategoryUpdatedEvent.builder()
                .categoryId(command.getCategoryId())
                .name(command.getName())
                .icon(command.getIcon())
                .level(command.getLevel())
                .sort(command.getSort())
                .spuCount(command.getSpuCount())
                .spuUnit(command.getSpuUnit())
                .showState(command.getShowState())
                .navState(command.getNavState())
                .keywords(command.getKeywords())
                .parentId(command.getParentId())
                .description(command.getDescription())
                .updatedBy(command.getUpdatedBy())
                .updatedAt(Instant.now())
                .build()
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(RemoveCategoryCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>removing category aggregate command, parameters: {}", command.toString());

        if (YesNoEnum.N.equals(getRemoved())) {
            apply(new CategoryRemovedEvent(
                    command.getCategoryId(),
                    command.getUpdatedBy())
            );
        }
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(RecoverCategoryCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>recovering category aggregate command, parameters: {}", command.toString());

        if (YesNoEnum.Y.equals(getRemoved())) {
            apply(new CategoryRecoveredEvent(
                    command.getCategoryId(),
                    command.getUpdatedBy())
            );
        }
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(CategoryCreatedEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>creating category aggregate event, parameters: {}", event.toString());

        setCategoryId(event.getCategoryId());
        setName(event.getName());
        setLevel(event.getLevel());
        setIcon(event.getIcon());
        setSpuCount(event.getSpuCount());
        setSpuUnit(event.getSpuUnit());
        setNavState(event.getNavState());
        setShowState(event.getShowState());
        setSort(event.getSort());
        setRemoved(event.getRemoved());
        setKeywords(event.getKeywords());
        setDescription(event.getDescription());
        setParentId(event.getParentId());
        setCreatedBy(event.getCreatedBy());
        setCreatedAt(event.getCreatedAt());
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(CategoryUpdatedEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>updating category aggregate event, parameters: {}", event.toString());

        setCategoryId(event.getCategoryId());
        setName(event.getName());
        setIcon(event.getIcon());
        setLevel(event.getLevel());
        setSort(event.getSort());
        setSpuCount(event.getSpuCount());
        setSpuUnit(event.getSpuUnit());
        setNavState(event.getNavState());
        setShowState(event.getShowState());
        setKeywords(event.getKeywords());
        setDescription(event.getDescription());
        setParentId(event.getParentId());
        setUpdatedBy(event.getUpdatedBy());
        setUpdatedAt(event.getUpdatedAt());
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(CategoryRemovedEvent event) {
        setRemoved(event.getState());
        updateStateChangedValue(event);
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(CategoryRecoveredEvent event) {
        setRemoved(event.getState());
        updateStateChangedValue(event);
    }

    /**
     * 更新状态变更后的属性值
     * @param event 状态变更事件
     */
    private void updateStateChangedValue(CategoryStateChangedEvent event) {
        setUpdatedBy(event.getUpdatedBy());
        setUpdatedAt(event.getUpdatedAt());
    }
}
