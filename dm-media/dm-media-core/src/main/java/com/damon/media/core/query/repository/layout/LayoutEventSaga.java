package com.damon.media.core.query.repository.layout;

import com.damon.media.domain.slot.layout.entity.FeedLayoutEntry;
import com.damon.media.domain.slot.layout.entity.FeedLayoutEntryRepository;
import com.damon.media.domain.slot.layout.entity.QFeedLayoutEntry;
import com.damon.media.domain.slot.layout.event.LayoutActivatedEvent;
import com.damon.media.domain.slot.layout.event.LayoutCreatedEvent;
import com.damon.media.domain.slot.layout.event.LayoutDeactivatedEvent;
import com.damon.media.domain.slot.layout.event.LayoutUpdatedEvent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Damon S.
 */
@Component
public class LayoutEventSaga {
    private static final Logger LOGGER = LoggerFactory.getLogger(LayoutEventSaga.class);

    private final FeedLayoutEntryRepository layoutEntryRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QFeedLayoutEntry qLayoutEntry;


    public LayoutEventSaga(FeedLayoutEntryRepository layoutEntryRepository,
                           EntityManagerProvider managerProvider) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.layoutEntryRepository = layoutEntryRepository;
        this.qLayoutEntry = QFeedLayoutEntry.feedLayoutEntry;
    }

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    protected void on(LayoutCreatedEvent event) {
        FeedLayoutEntry layoutEntry = FeedLayoutEntry.builder()
                .layoutId(event.getLayoutId().getValue())
                .width(event.getWidth())
                .height(event.getHeight())
                .layoutType(event.getType().name())
                .state(event.getState().name())
                .snapshot(event.getSnapshot())
                .createdBy(event.getCreatedBy())
                .updatedBy(event.getCreatedBy())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getCreatedAt())
                .build();
        layoutEntryRepository.save(layoutEntry);

        LOGGER.info("========>>Layout aggregate[Id:{}] created by User[Id:{}] at {} is successfully stored into Database.",
                event.getLayoutId().getValue(), event.getCreatedBy(), event.getCreatedAt());
    }


    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    protected void on(LayoutUpdatedEvent event) {
        jpaQueryFactory.update(qLayoutEntry)
                .set(qLayoutEntry.width, event.getWidth())
                .set(qLayoutEntry.height, event.getHeight())
                .set(qLayoutEntry.snapshot, event.getSnapshot())
                .set(qLayoutEntry.updatedBy, event.getUpdatedBy())
                .set(qLayoutEntry.updatedAt, event.getUpdatedAt())
                .where(qLayoutEntry.layoutId.eq(event.getLayoutId().getValue()))
                .execute();

        LOGGER.info("========>>Layout aggregate[Id:{}] is updated by User[Id:{}] at {} into Database.",
                event.getLayoutId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    protected void on(LayoutActivatedEvent event) {
        jpaQueryFactory.update(qLayoutEntry)
                .set(qLayoutEntry.state, event.getState().name())
                .set(qLayoutEntry.updatedBy, event.getUpdatedBy())
                .set(qLayoutEntry.updatedAt, event.getUpdatedAt())
                .where(qLayoutEntry.layoutId.eq(event.getLayoutId().getValue()))
                .execute();

        LOGGER.info("========>>Layout aggregate[Id:{}] is activated by User[Id:{}] at {} into Database.",
                event.getLayoutId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }


    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    protected void on(LayoutDeactivatedEvent event) {
        jpaQueryFactory.update(qLayoutEntry)
                .set(qLayoutEntry.state, event.getState().name())
                .set(qLayoutEntry.updatedBy, event.getUpdatedBy())
                .set(qLayoutEntry.updatedAt, event.getUpdatedAt())
                .where(qLayoutEntry.layoutId.eq(event.getLayoutId().getValue()))
                .execute();

        LOGGER.info("========>>Layout aggregate[Id:{}] is deactivated by User[Id:{}] at {} into Database.",
                event.getLayoutId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
