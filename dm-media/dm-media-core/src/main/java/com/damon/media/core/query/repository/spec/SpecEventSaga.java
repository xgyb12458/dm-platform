package com.damon.media.core.query.repository.spec;

import com.damon.media.domain.slot.spec.entity.QSlotSpecEntry;
import com.damon.media.domain.slot.spec.entity.SlotSpecEntry;
import com.damon.media.domain.slot.spec.entity.SlotSpecEntryRepository;
import com.damon.media.domain.slot.spec.event.SpecActivatedEvent;
import com.damon.media.domain.slot.spec.event.SpecCreatedEvent;
import com.damon.media.domain.slot.spec.event.SpecDeactivatedEvent;
import com.damon.media.domain.slot.spec.event.SpecUpdatedEvent;
import com.damon.shared.common.Constants;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.exception.SystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Damon S.
 */
@Component
public class SpecEventSaga {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecEventSaga.class);

    private final ObjectMapper mapper;
    private final SlotSpecEntryRepository specEntryRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QSlotSpecEntry qSlotSpecEntry;


    public SpecEventSaga(SlotSpecEntryRepository specEntryRepository,
                         EntityManagerProvider managerProvider) {
        this.mapper = new ObjectMapper();
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.specEntryRepository = specEntryRepository;
        this.qSlotSpecEntry = QSlotSpecEntry.slotSpecEntry;
    }

    @EventHandler
//    @SagaEventHandler()
    private void on(SpecCreatedEvent event) {
        SlotSpecEntry slotSpecEntry = SlotSpecEntry.builder()
                .specId(event.getSpecId().getValue())
                .width(event.getWidth())
                .height(event.getHeight())
                .imageType(event.getImageType())
                .imageSize(event.getImageSize())
                .snapshot(event.getSnapshot())
                .state(event.getState().name())
                .slotType(event.getType().name())
                .layoutIds(event.getLayoutIds().toString())
                .specExt(serializeSpecExt(event.getExt()))
                .createdBy(event.getCreatedBy())
                .updatedBy(event.getCreatedBy())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getCreatedAt())
                .build();
        this.specEntryRepository.saveAndFlush(slotSpecEntry);

        LOGGER.info("========>>Spec aggregate[Id:{}] created by User[Id:{}] at {} is successfully stored into Database.",
                event.getSpecId().getValue(), event.getCreatedBy(), event.getCreatedAt());
    }


    @EventHandler
//    @SagaEventHandler()
    private void on(SpecUpdatedEvent event) {
        jpaQueryFactory.update(qSlotSpecEntry)
                .set(qSlotSpecEntry.width, event.getWidth())
                .set(qSlotSpecEntry.height, event.getHeight())
                .set(qSlotSpecEntry.imageType, event.getImageType())
                .set(qSlotSpecEntry.imageSize, event.getImageSize())
                .set(qSlotSpecEntry.layoutIds, event.getLayoutIds().toString())
                .set(qSlotSpecEntry.snapshot, event.getSnapshot())
                .set(qSlotSpecEntry.specExt, serializeSpecExt(event.getExt()))
                .set(qSlotSpecEntry.updatedBy, event.getUpdatedBy())
                .set(qSlotSpecEntry.updatedAt, event.getUpdatedAt())
                .where(qSlotSpecEntry.specId.eq(event.getSpecId().getValue()))
                .execute();

        LOGGER.info("========>>Spec aggregate[Id:{}] is updated by User[Id:{}] at {} into Database.",
                event.getSpecId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @EventHandler
    private void on(SpecActivatedEvent event) {
        jpaQueryFactory.update(qSlotSpecEntry)
                .set(qSlotSpecEntry.state, event.getState().name())
                .set(qSlotSpecEntry.updatedBy, event.getUpdatedBy())
                .set(qSlotSpecEntry.updatedAt, event.getUpdateTime())
                .where(qSlotSpecEntry.specId.eq(event.getSpecId().getValue()))
                .execute();

        LOGGER.info("========>>Spec aggregate[Id:{}] is activated by User[Id:{}] at {} into Database.",
                event.getSpecId().getValue(), event.getUpdatedBy(), event.getUpdateTime());
    }


    @EventHandler
    private void on(SpecDeactivatedEvent event) {
        jpaQueryFactory.update(qSlotSpecEntry)
                .set(qSlotSpecEntry.state, event.getState().name())
                .set(qSlotSpecEntry.updatedBy, event.getUpdatedBy())
                .set(qSlotSpecEntry.updatedAt, event.getUpdateTime())
                .where(qSlotSpecEntry.specId.eq(event.getSpecId().getValue()))
                .execute();

        LOGGER.info("========>>Spec aggregate[Id:{}] is deactivated by User[Id:{}] at {} into Database.",
                event.getSpecId().getValue(), event.getUpdatedBy(), event.getUpdateTime());
    }


    private String serializeSpecExt(Object target) {
        String specExt = Constants.EMPTY;
        try {
            specExt = mapper.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            LOGGER.error("========>>序列化规格扩展字段(SpecExt)失败。");
            throw new SystemException(ResponseCodeEnum.INTERNAL_ERROR.getCode(), "序列化规格扩展字段(SpecExt)失败");
        }
        return specExt;
    }
}
