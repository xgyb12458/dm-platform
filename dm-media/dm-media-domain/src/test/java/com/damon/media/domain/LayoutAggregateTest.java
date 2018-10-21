package com.damon.media.domain;

import com.damon.media.domain.slot.layout.aggregate.LayoutAggregate;
import com.damon.media.domain.slot.layout.aggregate.LayoutId;
import com.damon.media.domain.slot.layout.command.ActivateLayoutCommand;
import com.damon.media.domain.slot.layout.command.CreateLayoutCommand;
import com.damon.media.domain.slot.layout.command.DeactivateLayoutCommand;
import com.damon.media.domain.slot.layout.command.UpdateLayoutCommand;
import com.damon.media.domain.slot.layout.event.LayoutActivatedEvent;
import com.damon.media.domain.slot.layout.event.LayoutCreatedEvent;
import com.damon.media.domain.slot.layout.event.LayoutDeactivatedEvent;
import com.damon.media.domain.slot.layout.event.LayoutUpdatedEvent;
import com.damon.media.shared.enums.LayoutType;
import com.damon.shared.enums.SwitchState;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

public class LayoutAggregateTest {
    private FixtureConfiguration<LayoutAggregate> aFixture;

    private CreateLayoutCommand createLayoutCommand;
    private LayoutCreatedEvent layoutCreatedEvent;
    private UpdateLayoutCommand updateLayoutCommand;
    private LayoutUpdatedEvent layoutUpdatedEvent;

    private ActivateLayoutCommand activateLayoutCommand;
    private LayoutActivatedEvent layoutActivatedEvent;
    private DeactivateLayoutCommand deactivateLayoutCommand;
    private LayoutDeactivatedEvent layoutDeactivatedEvent;

    @Before
    public void setUp() {
        aFixture = new AggregateTestFixture<>(LayoutAggregate.class);

        createLayoutCommand = CreateLayoutCommand.builder()
                .layoutId(new LayoutId())
                .width(750)
                .height(234)
                .state(SwitchState.ON)
                .type(LayoutType.TRIPLE_IMAGE)
                .snapshot("command.getSnapshot()")
                .createdBy(10000L)
                .build();


        layoutCreatedEvent = LayoutCreatedEvent.builder()
                .layoutId(createLayoutCommand.getLayoutId())
                .width(createLayoutCommand.getWidth())
                .height(createLayoutCommand.getHeight())
                .state(SwitchState.ON)
                .type(createLayoutCommand.getType())
                .snapshot(createLayoutCommand.getSnapshot())
                .createdBy(createLayoutCommand.getCreatedBy())
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        updateLayoutCommand = UpdateLayoutCommand.builder()
                .layoutId(createLayoutCommand.getLayoutId())
                .width(520)
                .height(150)
                .snapshot("command.getSnapshot()")
                .updatedBy(10000L)
                .build();


        layoutUpdatedEvent = LayoutUpdatedEvent.builder()
                .layoutId(updateLayoutCommand.getLayoutId())
                .width(updateLayoutCommand.getWidth())
                .height(updateLayoutCommand.getHeight())
                .snapshot(updateLayoutCommand.getSnapshot())
                .updatedBy(updateLayoutCommand.getUpdatedBy())
                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        activateLayoutCommand = new ActivateLayoutCommand(createLayoutCommand.getLayoutId(), 20000L);
        layoutActivatedEvent = new LayoutActivatedEvent(
                activateLayoutCommand.getLayoutId(),
                SwitchState.ON,
                activateLayoutCommand.getUpdatedBy(),
                activateLayoutCommand.getUpdatedAt());

        deactivateLayoutCommand = new DeactivateLayoutCommand(createLayoutCommand.getLayoutId(), 30000L);
        layoutDeactivatedEvent = new LayoutDeactivatedEvent(
                deactivateLayoutCommand.getLayoutId(),
                SwitchState.OFF,
                deactivateLayoutCommand.getUpdatedBy(),
                deactivateLayoutCommand.getUpdatedAt());
    }

    @Test
    public void testCreateLayoutFailed() {
        CreateLayoutCommand createCommand = CreateLayoutCommand.builder()
                .layoutId(createLayoutCommand.getLayoutId())
                .width(750)
                .height(234)
//                .layoutType(FeedLayout.STYLE.TRIPLE_IMAGE)
                .build();

        aFixture.givenNoPriorActivity()
                .when(createCommand)
                .expectNoEvents()
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateLayout() {
        aFixture.givenNoPriorActivity()
                .when(createLayoutCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(layoutCreatedEvent);
    }

    @Test
    public void testUpdateLayout() {
        aFixture.given(layoutCreatedEvent)
                .when(updateLayoutCommand)
                .expectEvents(layoutUpdatedEvent);
    }

    @Test
    public void testActivateLayout() {
        aFixture.given(layoutCreatedEvent, layoutDeactivatedEvent)
                .when(activateLayoutCommand)
                .expectEvents(layoutActivatedEvent);
    }

    @Test
    public void testActivateLayoutOnActivated() {
        aFixture.given(layoutCreatedEvent)
                .when(activateLayoutCommand)
                .expectNoEvents();
    }

    @Test
    public void testDeactivateLayout() {
        aFixture.given(layoutCreatedEvent)
                .when(deactivateLayoutCommand)
                .expectEvents(layoutDeactivatedEvent);
    }

    @Test
    public void testDeactivateLayoutOnDeactivated() {
        aFixture.given(layoutCreatedEvent, layoutDeactivatedEvent)
                .when(deactivateLayoutCommand)
                .expectNoEvents();
    }
}

