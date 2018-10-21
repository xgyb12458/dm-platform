package com.damon.media.core.query.repository.app;

import com.damon.media.domain.app.entity.AppEntry;
import com.damon.media.domain.app.entity.AppEntryRepository;
import com.damon.media.domain.app.entity.QAppEntry;
import com.damon.media.domain.app.event.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 将App事件对聚合的修改持久化到数据库中
 * @author Damon S.
 */
@Component
public final class AppEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppEventListener.class);

    private final JPAQueryFactory jpaQueryFactory;
    private final AppEntryRepository appEntryRepository;
    private final QAppEntry qAppEntry;

    public AppEventListener(AppEntryRepository appEntryRepository,
                            EntityManagerProvider managerProvider) {
        this.jpaQueryFactory = new JPAQueryFactory(
                managerProvider.getEntityManager()
        );
        this.qAppEntry = QAppEntry.appEntry;
        this.appEntryRepository = appEntryRepository;
    }

    @EventHandler
    public void on(AppCreatedEvent event) {
        AppEntry appEntry = AppEntry.builder()
                .appId(event.getAppId().getValue())
                .name(event.getName())
                .os(event.getOs().name())
                .appKey(event.getAccessEncrypt().getAppKey())
                .secret(event.getAccessEncrypt().getSecret())
                .type(event.getType().name())
                .status(event.getStatus().name())
                .state(event.getState().name())
                .source(event.getSource().name())
                .downloadUrl(event.getDownloadUrl())
                .packageName(event.getPackageName())
                .industry(event.getIndustry())
                .category(event.getCategory())
                .keywords(event.getKeywords())
                .snapshot(event.getSnapshot())
                .description(event.getDescription())
                .userId(event.getUserId())
                .createdBy(event.getCreatedBy())
//                .createdAt(event.getCreatedAt())
                .updatedBy(event.getCreatedBy())
//                .updatedAt(event.getCreatedAt())
                .build();
        appEntryRepository.saveAndFlush(appEntry);

        LOGGER.info("========>>App aggregate[Id:{}, name:'{}'] created by User[Id:{}] at {} is successfully stored into Database.",
                event.getAppId().getValue(), event.getName(), event.getCreatedBy(), event.getCreatedAt());
    }

    @EventHandler
    public void on(AppUpdatedEvent event) {
        jpaQueryFactory.update(qAppEntry)
                .set(qAppEntry.status, event.getStatus().name())
                .set(qAppEntry.industry, event.getIndustry())
                .set(qAppEntry.category, event.getCategory())
                .set(qAppEntry.keywords, event.getKeywords())
                .set(qAppEntry.snapshot, event.getSnapshot())
                .set(qAppEntry.description, event.getDescription())
                .set(qAppEntry.updatedBy, event.getUpdatedBy())
//                .set(qAppEntry.updatedAt, event.getUpdatedAt())
                .where(qAppEntry.appId.eq(event.getAppId().getValue()))
                .execute();

        LOGGER.info("========>>App aggregate[Id:{}] is updated by User[Id:{}] at {} into Database.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventHandler
    public void on(AppActivatedEvent event) {
        jpaQueryFactory.update(qAppEntry)
                .set(qAppEntry.state, event.getState().name())
                .set(qAppEntry.updatedBy, event.getUpdatedBy())
//                .set(qAppEntry.updatedAt, event.getUpdatedAt())
                .where(qAppEntry.appId.eq(event.getAppId().getValue()))
                .execute();

        LOGGER.info("========>>App aggregate[Id:{}] is activated by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventHandler
    public void on(AppDeactivatedEvent event) {
        jpaQueryFactory.update(qAppEntry)
                .set(qAppEntry.state, event.getState().name())
                .set(qAppEntry.updatedBy, event.getUpdatedBy())
//                .set(qAppEntry.updatedAt, event.getUpdatedAt())
                .where(qAppEntry.appId.eq(event.getAppId().getValue()))
                .execute();

        LOGGER.info("========>>App aggregate[Id:{}] is deactivated by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventHandler
    public void on(AppPassedEvent event) {
        jpaQueryFactory.update(qAppEntry)
                .set(qAppEntry.status, event.getStatus().name())
                .set(qAppEntry.updatedBy, event.getUpdatedBy())
//                .set(qAppEntry.updatedAt, event.getUpdatedAt())
                .where(qAppEntry.appId.eq(event.getAppId().getValue()))
                .execute();

        LOGGER.info("========>>App aggregate[Id:{}] is passed by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventHandler
    public void on(AppRejectedEvent event) {
        jpaQueryFactory.update(qAppEntry)
                .set(qAppEntry.status, event.getStatus().name())
                .set(qAppEntry.updatedBy, event.getUpdatedBy())
//                .set(qAppEntry.updatedAt, event.getUpdatedAt())
                .where(qAppEntry.appId.eq(event.getAppId().getValue()))
                .execute();

        LOGGER.info("========>>App aggregate[Id:{}] is rejected by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
