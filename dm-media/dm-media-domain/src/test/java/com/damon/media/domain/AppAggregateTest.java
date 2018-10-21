package com.damon.media.domain;

import com.damon.media.domain.app.aggregate.AppAggregate;
import com.damon.media.domain.app.aggregate.AppId;
import com.damon.media.domain.app.command.*;
import com.damon.media.domain.app.event.*;
import com.damon.media.shared.enums.MediaType;
import com.damon.shared.enums.AuditStatus;
import com.damon.shared.enums.OSCategory;
import com.damon.shared.enums.SwitchState;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.time.Instant;

public class AppAggregateTest {
    private FixtureConfiguration<AppAggregate> aFixture;

    private CreateAppCommand createAppCommand;
    private AppCreatedEvent appCreatedEvent;
    private UpdateAppCommand updateAppCommand;
    private AppUpdatedEvent appUpdatedEvent;

    private ActivateAppCommand activateAppCommand;
    private AppActivatedEvent appActivatedEvent;
    private DeactivateAppCommand deactivateAppCommand;
    private AppDeactivatedEvent appDeactivatedEvent;

    private PassAppCommand passAppCommand;
    private AppPassedEvent appPassedEvent;
    private RejectAppCommand rejectAppCommand;
    private AppRejectedEvent appRejectedEvent;

    @Before
    public void setUp() {
        aFixture = new AggregateTestFixture<>(AppAggregate.class);

        createAppCommand = CreateAppCommand.builder()
                .appId(new AppId())
                .name("飞凡APP")
                .os(OSCategory.IOS)
                .type(MediaType.APP)
                .downloadUrl("url")
                .packageName("package")
                .industry("industry")
                .category("category")
                .keywords("keywords")
                .description("description")
                .userId(10000L)
                .createdBy(10000L)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        appCreatedEvent = AppCreatedEvent.builder()
                .appId(createAppCommand.getAppId())
                .name(createAppCommand.getName())
                .os(createAppCommand.getOs())
                .type(createAppCommand.getType())
                .state(SwitchState.ON)
                .status(AuditStatus.AUDITING)
                .accessEncrypt(accessEncrypt(createAppCommand))
                .downloadUrl(createAppCommand.getDownloadUrl())
                .packageName(createAppCommand.getPackageName())
                .industry(createAppCommand.getIndustry())
                .category(createAppCommand.getCategory())
                .keywords(createAppCommand.getKeywords())
                .description(createAppCommand.getDescription())
                .userId(createAppCommand.getUserId())
                .createdBy(createAppCommand.getCreatedBy())
//                .createdAt(createAppCommand.getCreatedAt())
                .build();

        updateAppCommand = UpdateAppCommand.builder()
                .appId(createAppCommand.getAppId())
                .industry("createAppCommand.getIndustry()")
                .category("createAppCommand.getCategory()")
                .keywords("createAppCommand.getKeywords()")
                .description("createAppCommand.getDescription()")
                .updatedBy(90000L)
                .build();

        appUpdatedEvent = AppUpdatedEvent.builder()
                .appId(updateAppCommand.getAppId())
                .status(AuditStatus.AUDITING)
                .industry(updateAppCommand.getIndustry())
                .category(updateAppCommand.getCategory())
                .keywords(updateAppCommand.getKeywords())
                .description(updateAppCommand.getDescription())
                .updatedBy(updateAppCommand.getUpdatedBy())
//                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        activateAppCommand = new ActivateAppCommand(createAppCommand.getAppId(), 20000L);
        appActivatedEvent = new AppActivatedEvent(
                activateAppCommand.getAppId(),
                SwitchState.ON,
                activateAppCommand.getUpdatedBy());

        deactivateAppCommand = new DeactivateAppCommand(createAppCommand.getAppId(), 30000L);
        appDeactivatedEvent = new AppDeactivatedEvent(
                deactivateAppCommand.getAppId(),
                SwitchState.OFF,
                deactivateAppCommand.getUpdatedBy());

        passAppCommand = new PassAppCommand(createAppCommand.getAppId(), 40000L);
        appPassedEvent = new AppPassedEvent(
                passAppCommand.getAppId(),
                AuditStatus.APPROVED,
                passAppCommand.getUpdatedBy());

        rejectAppCommand = new RejectAppCommand(createAppCommand.getAppId(), 50000L);
        appRejectedEvent = new AppRejectedEvent(
                rejectAppCommand.getAppId(),
                AuditStatus.REJECTED,
                rejectAppCommand.getUpdatedBy());
    }

    private AppAggregate.AccessEncrypt accessEncrypt(CreateAppCommand command) {
        String aAppKey = "ff" + Long.toHexString(command.getCreatedAt().getTime());
        String aSecret = DigestUtils.md5DigestAsHex(
                (command.getName() + aAppKey + command.getOs().name() + command.getType().name()).getBytes()
        );
        return new AppAggregate.AccessEncrypt(aAppKey, aSecret);
    }

    @Test
    public void createAppSucceedWithCreatedEventFired() {
        aFixture.givenNoPriorActivity()
                .when(createAppCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(appCreatedEvent);
    }

    @Test
    public void createAppFailedBecauseOfIllegalArguments() {
        CreateAppCommand createCommand = CreateAppCommand.builder()
                .appId(createAppCommand.getAppId())
                .name(createAppCommand.getName())
//                .os(createAppCommand.getOs())
                .type(createAppCommand.getType())
                .createdAt(createAppCommand.getCreatedAt())
                .build();

        aFixture.givenNoPriorActivity()
                .when(createCommand)
                .expectNoEvents()
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void updateAppSucceedWithUpdatedEventFired() {
        aFixture.given(appCreatedEvent)
                .when(updateAppCommand)
                .expectEvents(appUpdatedEvent);
    }

    @Test
    public void passAppSucceedWithPassedEventFired() {
        aFixture.given(appCreatedEvent)
                .when(passAppCommand)
                .expectEvents(appPassedEvent);
    }

    @Test
    public void passAppOnPassedSucceedWithoutEventFired() {
        aFixture.given(appCreatedEvent, appPassedEvent)
                .when(passAppCommand)
                .expectNoEvents();
    }

    @Test
    public void passAppAfterRejectedSucceedWithPassedEventFired() {
        aFixture.given(appCreatedEvent, appRejectedEvent)
                .when(passAppCommand)
                .expectEvents(appPassedEvent);
    }

    @Test
    public void rejectAppSucceedWithRejectedEventFired() {
        aFixture.given(appCreatedEvent)
                .when(rejectAppCommand)
                .expectEvents(appRejectedEvent);
    }

    @Test
    public void rejectAppOnRejectedSucceedWithoutEventFIred() {
        aFixture.given(appCreatedEvent, appRejectedEvent)
                .when(rejectAppCommand)
                .expectNoEvents();
    }

    @Test
    public void rejectAppAfterPassedSucceedWithRejectedEventFired() {
        aFixture.given(appCreatedEvent, appPassedEvent)
                .when(rejectAppCommand)
                .expectEvents(appRejectedEvent);
    }

    @Test
    public void activateAppSucceedWithActivatedEventFired() {
        aFixture.given(appCreatedEvent, appDeactivatedEvent)
                .when(activateAppCommand)
                .expectEvents(appActivatedEvent);
    }

    @Test
    public void activateAppOnActivatedSicceedWithoutEventFired() {
        aFixture.given(appCreatedEvent)
                .when(activateAppCommand)
                .expectNoEvents();
    }

    @Test
    public void deactivateAppSucceedWithDeactivatedEventFired() {
        aFixture.given(appCreatedEvent)
                .when(deactivateAppCommand)
                .expectEvents(appDeactivatedEvent);
    }

    @Test
    public void deactivateAppOnDeactivatedSucceedWithoutEventFired() {
        aFixture.given(appCreatedEvent, appDeactivatedEvent)
                .when(deactivateAppCommand)
                .expectNoEvents();
    }
}

