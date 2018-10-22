package com.damon.media.domain.app.aggregate;

import com.damon.media.domain.app.command.ActivateAppCommand;
import com.damon.media.domain.app.command.DeactivateAppCommand;
import com.damon.media.domain.app.command.PassAppCommand;
import com.damon.media.domain.app.command.RejectAppCommand;
import com.damon.media.domain.app.event.*;
import com.damon.media.shared.enums.MediaSource;
import com.damon.media.shared.enums.MediaType;
import com.damon.shared.enums.*;
import com.damon.shared.exception.BusinessException;
import com.damon.shared.model.ValueObject;
import com.damon.media.domain.app.command.CreateAppCommand;
import com.damon.media.domain.app.command.UpdateAppCommand;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


/**
 * 媒体应用聚合
 * @author Damon S.
 */
@ToString
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public final class AppAggregate {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppAggregate.class);

    @AggregateIdentifier
    private AppId appId;
    private String name;
    private AccessEncrypt accessEncrypt;
    private MediaType type;
    private MediaSource source;
    private SwitchState state;
    private AuditStatus status;
    private OSCategory os;
    private String downloadUrl;
    private String packageName;
    private String industry;
    private String category;
    private String keywords;
    private String snapshot;
    private String description;
    private Long userId;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Autowired
    private Repository<AppAggregate> appRepository;


    @CommandHandler
    public AppAggregate(CreateAppCommand command) {
        // TODO: 检查name+type是否重复，重复则抛出异常
        apply(AppCreatedEvent.builder()
                .appId(command.getAppId())
                .name(command.getName())
                .os(command.getOs())
                .accessEncrypt(AccessEncrypt.encrypt(
                        command.getName(),
                        command.getOs(),
                        command.getType())
                )
                .type(command.getType())
                .state(SwitchState.ON)
                .source(command.getSource())
                .status(AuditStatus.AUDITING)
                .downloadUrl(command.getDownloadUrl())
                .packageName(command.getPackageName())
                .industry(command.getIndustry())
                .category(command.getCategory())
                .keywords(command.getKeywords())
                .snapshot(command.getSnapshot())
                .description(command.getDescription())
                .userId(command.getUserId())
                .createdBy(command.getCreatedBy())
                .createdAt(LocalDateTime.now())
                .build());
    }


    @SuppressWarnings("unused")
    @CommandHandler
    private void handle(UpdateAppCommand command) {
        apply(AppUpdatedEvent.builder()
                .appId(command.getAppId())
                .status(AuditStatus.AUDITING)
                .category(command.getCategory())
                .industry(command.getIndustry())
                .keywords(command.getKeywords())
                .snapshot(command.getSnapshot())
                .description(command.getDescription())
                .updatedBy(command.getUpdatedBy())
                .build());
    }


    @SuppressWarnings("unused")
    @CommandHandler
    private void handle(DeactivateAppCommand command) {
        if (this.getState() == command.getState()) {
            return;
        }
        apply(new AppDeactivatedEvent(
                command.getAppId(),
                command.getState(),
                command.getUpdatedBy()
        ));
    }


    @SuppressWarnings("unused")
    @CommandHandler
    private void handle(ActivateAppCommand command) {
        if (this.getState() == command.getState()) {
            return;
        }
        apply(new AppActivatedEvent(
                command.getAppId(),
                command.getState(),
                command.getUpdatedBy()
        ));
    }


    @SuppressWarnings("unused")
    @CommandHandler
    private void handle(PassAppCommand command) {
        if (this.getStatus() == command.getStatus()) {
            return;
        }
        checkAppStateBeforeAudit(command.getStatus());
        apply(new AppPassedEvent(
                command.getAppId(),
                command.getStatus(),
                command.getUpdatedBy()
        ));
    }


    @SuppressWarnings("unused")
    @CommandHandler
    private void handle(RejectAppCommand command) {
        if (this.getStatus() == command.getStatus()) {
            return;
        }
        apply(new AppRejectedEvent(
                command.getAppId(),
                command.getStatus(),
                command.getUpdatedBy()
        ));
    }


    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(AppCreatedEvent event) {
        this.setAppId(event.getAppId());
        this.setName(event.getName());
        this.setOs(event.getOs());
        this.setAccessEncrypt(event.getAccessEncrypt());
        this.setType(event.getType());
        this.setStatus(event.getStatus());
        this.setSource(event.getSource());
        this.setState(event.getState());
        this.setDownloadUrl(event.getDownloadUrl());
        this.setPackageName(event.getPackageName());
        this.setIndustry(event.getIndustry());
        this.setCategory(event.getCategory());
        this.setKeywords(event.getKeywords());
        this.setSnapshot(event.getSnapshot());
        this.setDescription(event.getDescription());
        this.setUserId(event.getUserId());
        this.setCreatedBy(event.getCreatedBy());
        this.setUpdatedBy(event.getCreatedBy());
        this.setCreatedAt(event.getCreatedAt());
        this.setUpdatedAt(event.getCreatedAt());

        LOGGER.info("========>>App aggregate[Id:{}, name:'{}'] is created by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getName(), event.getCreatedBy(), event.getCreatedAt());
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(AppUpdatedEvent event) {
        this.setIndustry(event.getIndustry());
        this.setCategory(event.getCategory());
        this.setStatus(event.getStatus());
        this.setKeywords(event.getKeywords());
        this.setSnapshot(event.getSnapshot());
        this.setDescription(event.getDescription());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdatedAt());

        LOGGER.info("========>>App aggregate[Id:{}] is updated by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(AppPassedEvent event) {
        this.setStatus(event.getStatus());
        this.eventFiredStamp(event);

        LOGGER.info("========>>App aggregate[Id:{}] is passed by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(AppRejectedEvent event) {
        this.setStatus(event.getStatus());
        this.eventFiredStamp(event);

        LOGGER.info("========>>App aggregate[Id:{}] is rejected by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(AppActivatedEvent event) {
        this.setState(event.getState());
        this.eventFiredStamp(event);

        LOGGER.info("========>>App aggregate[Id:{}] is activated by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(AppDeactivatedEvent event) {
        this.setState(event.getState());
        this.eventFiredStamp(event);

        LOGGER.info("========>>App aggregate[Id:{}] is deactivated by User[Id:{}] at {}.",
                event.getAppId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    private void eventFiredStamp(AppStateChangedEvent event) {
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdatedAt());
    }

    private void checkAppStateBeforeAudit(AuditStatus status) {
        if (!AuditStatus.AUDITING.equals(status)) {
            throw new BusinessException(ResponseCodeEnum.BIZ_ERROR, "媒体未处于待审核状态");
        }
    }



    /***
     * App应用访问密钥
     * @author Damon S.
     */
    @Getter
    @AllArgsConstructor
    public static final class AccessEncrypt
            implements ValueObject<AccessEncrypt> {
        private final String appKey;
        private final String secret;
        private static final String PREFIX = "dm";


        /***
         * 生成APP应用的访问密钥,
         * 输入的appName不变则生成的密钥也不变.
         * @param appName 媒体名称
         * @param os 媒体操作系统类型
         * @param type 媒体类型
         */
        private static AccessEncrypt encrypt(String appName,
                                             OSCategory os,
                                             MediaType type) {
            Assert.notNull(appName, () -> "应用名称不能为空");
            Assert.notNull(os, () -> "操作系统不能为空");
            Assert.notNull(type, () -> "应用类型不能为空");

            String aAppKey = PREFIX + Long.toHexString(System.currentTimeMillis());
            String aSecret = DigestUtils.md5DigestAsHex(
                    (appName + aAppKey + os.name() + type.name()).getBytes()
            );
            return new AccessEncrypt(aAppKey, aSecret);
        }

        @Override
        public boolean sameAs(AccessEncrypt o) {
            return this == o
                    || !ObjectUtils.isEmpty(o)
                    && getClass() == o.getClass()
                    && getAppKey().equals(o.getAppKey())
                    && getSecret().equals(o.getSecret());
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof AccessEncrypt) {
                return sameAs((AccessEncrypt)o);
            }
            return Boolean.FALSE;
        }
    }
}
