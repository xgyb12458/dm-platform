package com.damon.media.domain;

import com.damon.media.domain.slot.spec.aggregate.SpecAggregate;
import com.damon.media.domain.slot.spec.aggregate.SpecId;
import com.damon.media.domain.slot.spec.command.ActivateSpecCommand;
import com.damon.media.domain.slot.spec.command.CreateSpecCommand;
import com.damon.media.domain.slot.spec.command.DeactivateSpecCommand;
import com.damon.media.domain.slot.spec.command.UpdateSpecCommand;
import com.damon.media.domain.slot.spec.event.SpecActivatedEvent;
import com.damon.media.domain.slot.spec.event.SpecCreatedEvent;
import com.damon.media.domain.slot.spec.event.SpecDeactivatedEvent;
import com.damon.media.domain.slot.spec.event.SpecUpdatedEvent;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

public class SpecAggregateTest {
    private FixtureConfiguration<SpecAggregate> aFixture;

    private CreateSpecCommand createSpecCommand;
    private SpecCreatedEvent specCreatedEvent;
    private UpdateSpecCommand updateSpecCommand;
    private SpecUpdatedEvent specUpdatedEvent;

    private ActivateSpecCommand activateSpecCommand;
    private SpecActivatedEvent specActivatedEvent;
    private DeactivateSpecCommand deactivateSpecCommand;
    private SpecDeactivatedEvent specDeactivatedEvent;


    @Before
    public void setUp() {
        aFixture = new AggregateTestFixture<>(SpecAggregate.class);

        createSpecCommand = CreateSpecCommand.builder()
                .specId(new SpecId())
                .width(750)
                .height(234)
                .imageType("PNG,JPG")
                .imageSize(100)
                .slotType(SlotType.BANNER)
                .snapshot("command.getSnapshot()")
                .createdBy(10000L)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        specCreatedEvent = SpecCreatedEvent.builder()
                .specId(createSpecCommand.getSpecId())
                .width(createSpecCommand.getWidth())
                .height(createSpecCommand.getHeight())
                .imageType(createSpecCommand.getImageType())
                .imageSize(createSpecCommand.getImageSize())
                .state(SwitchState.ON)
                .type(createSpecCommand.getSlotType())
                .snapshot(createSpecCommand.getSnapshot())
                .createdBy(createSpecCommand.getCreatedBy())
                .createdAt(createSpecCommand.getCreatedAt())
                .build();

        updateSpecCommand = UpdateSpecCommand.builder()
                .specId(createSpecCommand.getSpecId())
                .width(500)
                .height(200)
                .imageType("GIF,PNG,JPG")
                .imageSize(200)
                .snapshot("getSnapshot()")
                .updatedBy(90000L)
                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        specUpdatedEvent = SpecUpdatedEvent.builder()
                .specId(updateSpecCommand.getSpecId())
                .width(updateSpecCommand.getWidth())
                .height(updateSpecCommand.getHeight())
                .imageType(updateSpecCommand.getImageType())
                .imageSize(updateSpecCommand.getImageSize())
                .snapshot(updateSpecCommand.getSnapshot())
                .updatedBy(updateSpecCommand.getUpdatedBy())
                .updatedAt(updateSpecCommand.getUpdatedAt())
                .build();

        activateSpecCommand = new ActivateSpecCommand(createSpecCommand.getSpecId(), 20000L);
        specActivatedEvent = new SpecActivatedEvent(
                activateSpecCommand.getSpecId(),
                SwitchState.ON,
                activateSpecCommand.getUpdatedBy(),
                activateSpecCommand.getUpdateTime());

        deactivateSpecCommand = new DeactivateSpecCommand(createSpecCommand.getSpecId(), 30000L);
        specDeactivatedEvent = new SpecDeactivatedEvent(
                deactivateSpecCommand.getSpecId(),
                SwitchState.OFF,
                deactivateSpecCommand.getUpdatedBy(),
                deactivateSpecCommand.getUpdateTime());
    }

    @Test
    public void testCreateSpecFailed() {
        CreateSpecCommand createCommand = CreateSpecCommand.builder()
                .specId(createSpecCommand.getSpecId())
                .width(700)
                .height(200)
                .imageType("JPG")
                .imageSize(150)
//                .type(SlotAggregate.TYPE.BANNER)
                .build();

        aFixture.givenNoPriorActivity()
                .when(createCommand)
                .expectNoEvents()
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateSpec() {
        aFixture.givenNoPriorActivity()
                .when(createSpecCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(specCreatedEvent);
    }

    @Test
    public void testUpdateSpec() {
        aFixture.given(specCreatedEvent)
                .when(updateSpecCommand)
                .expectEvents(specUpdatedEvent);
    }

    @Test
    public void testActivateSpec() {
        aFixture.given(specCreatedEvent, specDeactivatedEvent)
                .when(activateSpecCommand)
                .expectEvents(specActivatedEvent);
    }

    @Test
    public void testActivateSpecOnActivated() {
        aFixture.given(specCreatedEvent)
                .when(activateSpecCommand)
                .expectNoEvents();
    }

    @Test
    public void testDeactivateSpec() {
        aFixture.given(specCreatedEvent)
                .when(deactivateSpecCommand)
                .expectEvents(specDeactivatedEvent);
    }

    @Test
    public void testDeactivateSpecOnDeactivated() {
        aFixture.given(specCreatedEvent, specDeactivatedEvent)
                .when(deactivateSpecCommand)
                .expectNoEvents();
    }
}

