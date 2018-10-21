package com.damon.media.core.query.repository.slot;

import com.damon.media.domain.slot.entity.QSlotEntry;
import com.damon.media.domain.slot.entity.SlotEntry;
import com.damon.media.domain.slot.entity.SlotEntryRepository;
import com.damon.media.domain.slot.event.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 广告位事件处理监听器
 * @author Damon S.
 */
@Component
public class SlotEventListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(SlotEventListener.class);

    private final JPAQueryFactory jpaQueryFactory;
    private final SlotEntryRepository slotEntryRepository;
    private final QSlotEntry qSlotEntry;


    public SlotEventListener(SlotEntryRepository slotEntryRepository,
                             EntityManagerProvider managerProvider) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.slotEntryRepository = slotEntryRepository;
        this.qSlotEntry = QSlotEntry.slotEntry;
    }

    @EventHandler
    private void on(SlotCreatedEvent event) {
        SlotEntry slotEntry = SlotEntry.builder()
                .slotId(event.getSlotId().getValue())
                .name(event.getName())
                .alias(event.getAlias())
                .type(event.getType().name())
                .state(event.getState().name())
                .status(event.getStatus().name())
                .channel(event.getChannel())
                .snapshot(event.getSnapshot())
                .blockIndustry(event.getBlockIndustry())
                .description(event.getDescription())
                .appIds(event.getAppIds().toString())
                .specId(event.getSpecId().getValue())
                .userId(event.getUserId())
                .createdBy(event.getCreatedBy())
                .updatedBy(event.getCreatedBy())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getCreatedAt())
                .build();
        this.slotEntryRepository.save(slotEntry);

        LOGGER.info("========>>Slot aggregate[Id:{}, name:'{}'] created by User[Id:{}] at {} is successfully stored into Database.",
                event.getSlotId().getValue(), event.getName(), event.getCreatedBy(), event.getCreatedAt());
    }


    @EventHandler
    private void on(SlotUpdatedEvent event) {
        jpaQueryFactory.update(qSlotEntry)
                .set(qSlotEntry.specId, event.getSpecId().getValue())
                .set(qSlotEntry.channel, event.getChannel())
                .set(qSlotEntry.blockIndustry, event.getBlockIndustry())
                .set(qSlotEntry.snapshot, event.getSnapshot())
                .set(qSlotEntry.appIds, event.getAppIds().toString())
                .set(qSlotEntry.description, event.getDescription())
                .set(qSlotEntry.updatedBy, event.getUpdatedBy())
                .set(qSlotEntry.updatedAt, event.getUpdateTime())
                .where(qSlotEntry.slotId.eq(event.getSlotId().getValue()))
                .execute();

        LOGGER.info("========>>Slot aggregate[Id:{}] is updated by User[Id:{}] at {} into Database.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdateTime());
    }


    @EventHandler
    private void on(SlotActivatedEvent event) {
        jpaQueryFactory.update(qSlotEntry)
                .set(qSlotEntry.state, event.getState().name())
                .set(qSlotEntry.updatedBy, event.getUpdatedBy())
                .set(qSlotEntry.updatedAt, event.getUpdatedAt())
                .where(qSlotEntry.slotId.eq(event.getSlotId().getValue()))
                .execute();

        LOGGER.info("========>>Slot aggregate[Id:{}] is activated by User[Id:{}] at {} into Database.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @EventHandler
    private void on(SlotDeactivatedEvent event) {
        jpaQueryFactory.update(qSlotEntry)
                .set(qSlotEntry.state, event.getState().name())
                .set(qSlotEntry.updatedBy, event.getUpdatedBy())
                .set(qSlotEntry.updatedAt, event.getUpdatedAt())
                .where(qSlotEntry.slotId.eq(event.getSlotId().getValue()))
                .execute();

        LOGGER.info("========>>Slot aggregate[Id:{}] is deactivated by User[Id:{}] at {} into Database.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @EventHandler
    private void on(SlotPassedEvent event) {
        jpaQueryFactory.update(qSlotEntry)
                .set(qSlotEntry.status, event.getStatus().name())
                .set(qSlotEntry.updatedBy, event.getUpdatedBy())
                .set(qSlotEntry.updatedAt, event.getUpdatedAt())
                .where(qSlotEntry.slotId.eq(event.getSlotId().getValue()))
                .execute();

        LOGGER.info("========>>Slot aggregate[Id:{}] is passed by User[Id:{}] at {} into Database.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @EventHandler
    private void on(SlotRejectedEvent event) {
        jpaQueryFactory.update(qSlotEntry)
                .set(qSlotEntry.status, event.getStatus().name())
                .set(qSlotEntry.updatedBy, event.getUpdatedBy())
                .set(qSlotEntry.updatedAt, event.getUpdatedAt())
                .where(qSlotEntry.slotId.eq(event.getSlotId().getValue()))
                .execute();

        LOGGER.info("========>>Slot aggregate[Id:{}] is rejected by User[Id:{}] at {} into Database.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
