package com.damon.media.domain;

import com.damon.media.domain.slot.aggregate.SlotAggregate;
import com.damon.media.domain.slot.aggregate.SlotId;
import com.damon.media.domain.slot.command.*;
import com.damon.media.domain.slot.event.*;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.AuditStatus;
import com.damon.shared.enums.SwitchState;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class SlotAggregateTest {
    private FixtureConfiguration<SlotAggregate> aFixture;

    private CreateSlotCommand createSlotCommand;
    private SlotCreatedEvent slotCreatedEvent;
    private UpdateSlotCommand updateSlotCommand;
    private SlotUpdatedEvent slotUpdatedEvent;

    private ActivateSlotCommand activateSlotCommand;
    private SlotActivatedEvent slotActivatedEvent;
    private DeactivateSlotCommand deactivateSlotCommand;
    private SlotDeactivatedEvent slotDeactivatedEvent;

    private PassSlotCommand passSlotCommand;
    private SlotPassedEvent slotPassedEvent;
    private RejectSlotCommand rejectSlotCommand;
    private SlotRejectedEvent slotRejectedEvent;

    @Before
    public void setUp() {
        aFixture = new AggregateTestFixture<>(SlotAggregate.class);

        createSlotCommand = CreateSlotCommand.builder()
                .slotId(new SlotId())
                .appIds(new ArrayList<>())
                .specId(10000000L)
                .name("createSlotReqDTO.getName()")
                .type(SlotType.BANNER)
                .channel("createSlotReqDTO.getChannel()")
                .snapshot("createSlotReqDTO.getSnapshot()")
                .blockIndustry("createSlotReqDTO.getBlockIndustry()")
                .description("createSlotReqDTO.getDescription()")
                .userId(10000L)
                .createdBy(10000L)
                .build();

        slotCreatedEvent = SlotCreatedEvent.builder()
                .slotId(createSlotCommand.getSlotId())
                .name(createSlotCommand.getName())
                .alias(DigestUtils.md5DigestAsHex(
                        (createSlotCommand.getName() + createSlotCommand.getType().name()).getBytes()
                ))
                .type(createSlotCommand.getType())
                .state(SwitchState.ON)
                .status(AuditStatus.AUDITING)
                .channel(createSlotCommand.getChannel())
                .snapshot(createSlotCommand.getSnapshot())
                .blockIndustry(createSlotCommand.getBlockIndustry())
                .description(createSlotCommand.getDescription())
                //.spec(createSlotCommand.getSpecId())
                .appIds(createSlotCommand.getAppIds())
                .userId(createSlotCommand.getUserId())
                .createdBy(createSlotCommand.getCreatedBy())
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        updateSlotCommand = UpdateSlotCommand.builder()
                .slotId(createSlotCommand.getSlotId())
                .channel(createSlotCommand.getChannel())
                .snapshot(createSlotCommand.getSnapshot())
                .blockIndustry(createSlotCommand.getBlockIndustry())
                .description(createSlotCommand.getDescription())
                .specId(createSlotCommand.getSpecId())
                .updatedBy(90000L)
                .updateTime(Timestamp.from(Instant.now()))
                .build();

        slotUpdatedEvent = SlotUpdatedEvent.builder()
                .slotId(updateSlotCommand.getSlotId())
                .channel(updateSlotCommand.getChannel())
                .snapshot(updateSlotCommand.getSnapshot())
                .blockIndustry(updateSlotCommand.getBlockIndustry())
                .description(updateSlotCommand.getDescription())
//                .spec(updateSlotCommand.getSpecId())
                .updatedBy(updateSlotCommand.getUpdatedBy())
                .updateTime(updateSlotCommand.getUpdateTime())
                .build();

        activateSlotCommand = new ActivateSlotCommand(createSlotCommand.getSlotId(), 20000L);
        slotActivatedEvent = new SlotActivatedEvent(
                activateSlotCommand.getSlotId(),
                SwitchState.ON,
                activateSlotCommand.getUpdatedBy(),
                activateSlotCommand.getUpdatedAt());

        deactivateSlotCommand = new DeactivateSlotCommand(createSlotCommand.getSlotId(), 30000L);
        slotDeactivatedEvent = new SlotDeactivatedEvent(
                deactivateSlotCommand.getSlotId(),
                SwitchState.OFF,
                deactivateSlotCommand.getUpdatedBy(),
                deactivateSlotCommand.getUpdatedAt());

        passSlotCommand = new PassSlotCommand(createSlotCommand.getSlotId(), 40000L);
        slotPassedEvent = new SlotPassedEvent(
                passSlotCommand.getSlotId(),
                AuditStatus.APPROVED,
                passSlotCommand.getUpdatedBy(),
                passSlotCommand.getUpdatedAt());

        rejectSlotCommand = new RejectSlotCommand(createSlotCommand.getSlotId(), 50000L);
        slotRejectedEvent = new SlotRejectedEvent(
                rejectSlotCommand.getSlotId(),
                AuditStatus.REJECTED,
                rejectSlotCommand.getUpdatedBy(),
                rejectSlotCommand.getUpdatedAt());
    }

    @Test
    public void testCreateSlotFailed() {
        CreateSlotCommand createCommand = CreateSlotCommand.builder()
                .slotId(createSlotCommand.getSlotId())
//                .name("百货轮播位")
                .type(SlotType.BANNER)
                .appIds(createSlotCommand.getAppIds())
                .build();

        aFixture.givenNoPriorActivity()
                .when(createCommand)
                .expectNoEvents()
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateSlot() {
        aFixture.givenNoPriorActivity()
                .when(createSlotCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(slotCreatedEvent);
    }

    @Test
    public void testUpdateSlot() {
        aFixture.given(slotCreatedEvent)
                .when(updateSlotCommand)
                .expectEvents(slotUpdatedEvent);
    }

    @Test
    public void testPassSlot() {
        aFixture.given(slotCreatedEvent)
                .when(passSlotCommand)
                .expectEvents(slotPassedEvent);
    }

    @Test
    public void testPassSlotOnPassed() {
        aFixture.given(slotCreatedEvent, slotPassedEvent)
                .when(passSlotCommand)
                .expectNoEvents();
    }

    @Test
    public void testPassSlotAfterRejected() {
        aFixture.given(slotCreatedEvent, slotRejectedEvent)
                .when(passSlotCommand)
                .expectEvents(slotPassedEvent);
    }

    @Test
    public void testRejectSlot() {
        aFixture.given(slotCreatedEvent)
                .when(rejectSlotCommand)
                .expectEvents(slotRejectedEvent);
    }

    @Test
    public void testRejectSlotOnRejected() {
        aFixture.given(slotCreatedEvent, slotRejectedEvent)
                .when(rejectSlotCommand)
                .expectNoEvents();
    }

    @Test
    public void testRejectSlotAfterPassed() {
        aFixture.given(slotCreatedEvent, slotPassedEvent)
                .when(rejectSlotCommand)
                .expectEvents(slotRejectedEvent);
    }

    @Test
    public void testActivateSlot() {
        aFixture.given(slotCreatedEvent, slotDeactivatedEvent)
                .when(activateSlotCommand)
                .expectEvents(slotActivatedEvent);
    }

    @Test
    public void testActivateSlotOnActivated() {
        aFixture.given(slotCreatedEvent)
                .when(activateSlotCommand)
                .expectNoEvents();
    }

    @Test
    public void testDeactivateSlot() {
        aFixture.given(slotCreatedEvent)
                .when(deactivateSlotCommand)
                .expectEvents(slotDeactivatedEvent);
    }

    @Test
    public void testDeactivateSlotOnDeactivated() {
        aFixture.given(slotCreatedEvent, slotDeactivatedEvent)
                .when(deactivateSlotCommand)
                .expectNoEvents();
    }
}

