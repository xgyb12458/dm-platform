package com.damon.product.domain.brand.aggregate;

import com.damon.product.domain.brand.command.*;
import com.damon.product.domain.brand.event.*;
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
 * 品牌
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月10日 13:38
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class BrandAggregate {
    @AggregateIdentifier
    private BrandId     brandId;
    private String      name;
    private String      code;
    private String      logo;
    private String      homepage;
    private Integer     sort;
    private YesNoEnum   factoryState;
    private YesNoEnum   removed;
    private YesNoEnum   display;
    private String      description;
    private String      firstLetter;
    private String      bigImage;
    private Long        createdBy;
    private Long        updatedBy;
    private Instant     createdAt;
    private Instant     updatedAt;


    @CommandHandler
    public BrandAggregate(CreateBrandCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>creating brand aggregate command, parameters: {}", command.toString());

        apply(BrandCreatedEvent.builder()
                .brandId(command.getBrandId())
                .name(command.getName())
                .code(command.getCode())
                .homepage(command.getHomepage())
                .logo(command.getLogo())
                .firstLetter(command.getFirstLetter())
                .sort(command.getSort())
                .display(command.getDisplay())
                .factoryState(command.getFactoryState())
                .removed(YesNoEnum.N)
                .bigImage(command.getBigImage())
                .description(command.getDescription())
                .createdBy(command.getCreatedBy())
                .createdAt(Instant.now())
                .build());
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(UpdateBrandCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>updating brand aggregate command, parameters: {}", command.toString());

        apply(BrandUpdatedEvent.builder()
                .brandId(command.getBrandId())
                .name(command.getName())
                .code(command.getCode())
                .homepage(command.getHomepage())
                .logo(command.getLogo())
                .firstLetter(command.getFirstLetter())
                .sort(command.getSort())
                .display(command.getDisplay())
                .factoryState(command.getFactoryState())
                .bigImage(command.getBigImage())
                .description(command.getDescription())
                .updatedBy(command.getUpdatedBy())
                .updatedAt(Instant.now())
                .build());
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(RemoveBrandCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>removing brand aggregate command, parameters: {}", command.toString());

        if (YesNoEnum.N.equals(getRemoved())) {
            apply(new BrandRemovedEvent(
                    command.getBrandId(),
                    command.getUpdatedBy())
            );
        }
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(RecoverBrandCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>recovering brand aggregate command, parameters: {}", command.toString());

        if (YesNoEnum.Y.equals(getRemoved())) {
            apply(new BrandRecoveredEvent(
                    command.getBrandId(),
                    command.getUpdatedBy())
            );
        }
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(ChangeBrandDisplayCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>changing brand aggregate display state command, parameters: {}", command.toString());

        apply(new BrandDisplayChangedEvent(
                command.getBrandId(),
                YesNoEnum.Y.equals(getDisplay()) ? YesNoEnum.N : YesNoEnum.Y,
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(ChangeBrandFactoryCommand command) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>changing brand aggregate factory state command, parameters: {}", command.toString());

        apply(new BrandFactoryChangedEvent(
                command.getBrandId(),
                YesNoEnum.Y.equals(getFactoryState()) ? YesNoEnum.N : YesNoEnum.Y,
                command.getUpdatedBy())
        );
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(BrandCreatedEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>creating brand aggregate event, parameters: {}", event.toString());

        setBrandId(event.getBrandId());
        setName(event.getName());
        setCode(event.getCode());
        setLogo(event.getLogo());
        setHomepage(event.getHomepage());
        setSort(event.getSort());
        setFactoryState(event.getFactoryState());
        setRemoved(event.getRemoved());
        setDisplay(event.getDisplay());
        setDescription(event.getDescription());
        setFirstLetter(event.getFirstLetter());
        setBigImage(event.getBigImage());
        setCreatedBy(event.getCreatedBy());
        setCreatedAt(event.getCreatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(BrandUpdatedEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>updating brand aggregate event, parameters: {}", event.toString());

        setName(event.getName());
        setCode(event.getCode());
        setLogo(event.getLogo());
        setSort(event.getSort());
        setHomepage(event.getHomepage());
        setBigImage(event.getBigImage());
        setDisplay(event.getDisplay());
        setFirstLetter(event.getFirstLetter());
        setFactoryState(event.getFactoryState());
        setDescription(event.getDescription());
        setUpdatedBy(event.getUpdatedBy());
        setUpdatedAt(event.getUpdatedAt());
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(BrandRemovedEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>removing brand aggregate event, parameters: {}", event.toString());

        setRemoved(event.getState());
        updateStateChangedValue(event);
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(BrandRecoveredEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>recovering brand aggregate event, parameters: {}", event.toString());

        setRemoved(event.getState());
        updateStateChangedValue(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(BrandDisplayChangedEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>changing brand aggregate display event, parameters: {}", event.toString());

        // 更新属性值
        setDisplay(event.getState());
        updateStateChangedValue(event);
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(BrandFactoryChangedEvent event) {
        log.info(Constants.PREFIX_PRODUCT + "==========>>changing brand aggregate factory state event, parameters: {}", event.toString());

        // 更新属性值
        setFactoryState(event.getState());
        updateStateChangedValue(event);
    }


    /**
     * 更新状态变更后的属性值
     * @param event 状态变更事件
     */
    private void updateStateChangedValue(BrandStateChangedEvent event) {
        setUpdatedBy(event.getUpdatedBy());
        setUpdatedAt(event.getUpdatedAt());
    }
}
