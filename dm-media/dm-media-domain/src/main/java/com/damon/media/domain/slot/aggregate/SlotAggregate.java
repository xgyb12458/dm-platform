package com.damon.media.domain.slot.aggregate;

import com.damon.media.domain.slot.command.*;
import com.damon.media.domain.slot.event.*;
import com.damon.media.domain.slot.spec.aggregate.SpecId;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.*;
import com.damon.shared.exception.BusinessException;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * 资源位
 * @author Damon S.
 */
@ToString
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class SlotAggregate {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlotAggregate.class);

    @AggregateIdentifier
    private SlotId slotId;
    private String name;
    private String alias;
    private SlotType type;
    private SwitchState state;
    private AuditStatus status;
    private OSCategory os;
    private String channel;
    private String snapshot;
    private String blockIndustry;
    private String description;
    private SpecId specId;
    private List<Long> appIds;
    private Long userId;
    private Long createdBy;
    private Long updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;


    public YesNoEnum getEditable() {
        // TODO:
        return YesNoEnum.YES;
    }


    @CommandHandler
    public SlotAggregate(CreateSlotCommand command) {
        Assert.nonNull(command.getName(), () -> "资源位名称不能为空");
        Assert.nonNull(command.getType(), () -> "资源位类型不能为空");

        String slotAlias = DigestUtils.md5DigestAsHex(
                (command.getName() + command.getType().name()).getBytes()
        );
        apply(SlotCreatedEvent.builder()
                .slotId(command.getSlotId())
                .name(command.getName())
                .alias(slotAlias)
                .type(command.getType())
                .state(SwitchState.ON)
                .status(AuditStatus.AUDITING)
                .os(command.getOs())
                .channel(command.getChannel())
                .snapshot(command.getSnapshot())
                .blockIndustry(command.getBlockIndustry())
                .description(command.getDescription())
                .specId(new SpecId(command.getSpecId()))
                .appIds(command.getAppIds())
                .userId(command.getUserId())
                .createdBy(command.getCreatedBy())
                .createdAt(Timestamp.from(Instant.now()))
                .build());
    }

    @CommandHandler
    private void handle(UpdateSlotCommand command) {
        if (YesNoEnum.NO.equals(this.getEditable())) {
            return;
        }
        apply(SlotUpdatedEvent.builder()
                .slotId(command.getSlotId())
                .channel(command.getChannel())
                .snapshot(command.getSnapshot())
                .os(command.getOs())
                .appIds(command.getAppIds())
                .blockIndustry(command.getBlockIndustry())
                .description(command.getDescription())
                .specId(new SpecId(command.getSpecId()))
                .updatedBy(command.getUpdatedBy())
                .updateTime(command.getUpdateTime())
                .build());
    }

    @CommandHandler
    private void handle(ActivateSlotCommand command) {
        if (this.getState() == command.getState()) {
            return;
        }
        apply(new SlotActivatedEvent(
                command.getSlotId(), command.getState(),
                command.getUpdatedBy(), command.getUpdatedAt()
        ));
    }

    @CommandHandler
    private void handle(DeactivateSlotCommand command) {
        if (this.getState() == command.getState()) {
            return;
        }
        apply(new SlotDeactivatedEvent(
                command.getSlotId(), command.getState(),
                command.getUpdatedBy(), command.getUpdatedAt()
        ));
    }

    @CommandHandler
    private void handle(PassSlotCommand command) {
        if (this.getStatus() == command.getStatus()) {
            return;
        }
        checkSlotStateBeforeAudit(command.getStatus());
        apply(new SlotPassedEvent(
                command.getSlotId(), command.getStatus(),
                command.getUpdatedBy(), command.getUpdatedAt()
        ));
    }

    @CommandHandler
    private void handle(RejectSlotCommand command) {
        if (this.getStatus() == command.getStatus()) {
            return;
        }
        apply(new SlotRejectedEvent(
                command.getSlotId(), command.getStatus(),
                command.getUpdatedBy(), command.getUpdatedAt()
        ));
    }

    @EventSourcingHandler
    private void on(SlotCreatedEvent event) {
        this.setSlotId(event.getSlotId());
        this.setName(event.getName());
        this.setAlias(event.getAlias());
        this.setType(event.getType());
        this.setState(event.getState());
        this.setStatus(event.getStatus());
        this.setOs(event.getOs());
        this.setChannel(event.getChannel());
        this.setSnapshot(event.getSnapshot());
        this.setBlockIndustry(event.getBlockIndustry());
        this.setDescription(event.getDescription());
        this.setAppIds(event.getAppIds());
        this.setSpecId(event.getSpecId());
        this.setUserId(event.getUserId());
        this.setCreatedBy(event.getCreatedBy());
        this.setUpdatedBy(event.getCreatedBy());
        this.setCreatedAt(event.getCreatedAt());
        this.setUpdatedAt(event.getCreatedAt());

        LOGGER.info("========>>Slot aggregate[Id:{}, name:'{}'] bound to Media[Id:{}] is created by User[Id:{}] at {}.",
                event.getSlotId().getValue(), event.getName(), event.getAppIds().toString(), event.getCreatedBy(), event.getCreatedAt());
    }

    @EventSourcingHandler
    private void on(SlotUpdatedEvent event) {
        this.setChannel(event.getChannel());
        this.setSnapshot(event.getSnapshot());
        this.setBlockIndustry(event.getBlockIndustry());
        this.setSpecId(event.getSpecId());
        this.setOs(event.getOs());
        this.setAppIds(event.getAppIds());
        this.setStatus(AuditStatus.AUDITING);
        this.setDescription(event.getDescription());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdateTime());

        LOGGER.info("========>>Slot aggregate[Id:{}] is updated by User[Id:{}] at {}.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdateTime());
    }

    @EventSourcingHandler
    private void on(SlotActivatedEvent event) {
        this.setState(event.getState());
        this.eventFiredStamp(event);

        LOGGER.info("========>>Slot aggregate[Id:{}] is activated by User[Id:{}] at {}.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventSourcingHandler
    private void on(SlotDeactivatedEvent event) {
        this.setState(event.getState());
        this.eventFiredStamp(event);

        LOGGER.info("========>>Slot aggregate[Id:{}] is deactivated by User[Id:{}] at {}.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventSourcingHandler
    private void on(SlotPassedEvent event) {
        this.setStatus(event.getStatus());
        this.eventFiredStamp(event);

        LOGGER.info("========>>Slot aggregate[Id:{}] is passed by User[Id:{}] at {}.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventSourcingHandler
    private void on(SlotRejectedEvent event) {
        this.setStatus(event.getStatus());
        this.eventFiredStamp(event);

        LOGGER.info("========>>Slot aggregate[Id:{}] is rejected by User[Id:{}] at {}.",
                event.getSlotId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    private void eventFiredStamp(SlotStateChangedEvent event) {
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdatedAt());
    }

    private void checkSlotStateBeforeAudit(AuditStatus status) {
        if (!AuditStatus.AUDITING.equals(status)) {
            throw new BusinessException(ResponseCodeEnum.BIZ_ERROR, "资源位未处于待审核状态");
        }
    }
}
