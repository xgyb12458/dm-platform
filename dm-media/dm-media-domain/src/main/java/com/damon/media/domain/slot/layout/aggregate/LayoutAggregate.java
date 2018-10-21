package com.damon.media.domain.slot.layout.aggregate;

import com.damon.media.domain.slot.layout.command.ActivateLayoutCommand;
import com.damon.media.domain.slot.layout.command.DeactivateLayoutCommand;
import com.damon.media.domain.slot.layout.event.LayoutActivatedEvent;
import com.damon.media.domain.slot.layout.event.LayoutDeactivatedEvent;
import com.damon.media.domain.slot.spec.aggregate.SpecAggregate;
import com.damon.media.shared.enums.LayoutType;
import com.damon.media.domain.slot.layout.command.CreateLayoutCommand;
import com.damon.media.domain.slot.layout.command.UpdateLayoutCommand;
import com.damon.media.domain.slot.layout.event.LayoutCreatedEvent;
import com.damon.media.domain.slot.layout.event.LayoutUpdatedEvent;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.enums.SwitchState;
import com.damon.shared.enums.YesNoEnum;
import com.damon.shared.exception.BusinessException;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * 信息流资源位规格样式
 * @author Damon S.
 */
@ToString
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class LayoutAggregate {
    private static final Logger LOGGER = LoggerFactory.getLogger(LayoutAggregate.class);

    @AggregateIdentifier
    private LayoutId layoutId;
    private Integer width;
    private Integer height;
    private Integer imageCount;
    private SwitchState state;
    private LayoutType layoutType;
    private String snapshot;
    private Long createdBy;
    private Long updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Autowired
    private Repository<SpecAggregate> specRepository;


    public YesNoEnum getEditable() {
        // TODO: 判断是否被其他规格引用
        return YesNoEnum.YES;
    }

    @CommandHandler
    public LayoutAggregate(CreateLayoutCommand command) {
        Assert.nonNull(command.getWidth(), () -> "宽度不能为空");
        Assert.nonNull(command.getHeight(), () -> "高度不能为空");
        Assert.nonNull(command.getType(), () -> "样式类型不能为空");

        apply(LayoutCreatedEvent.builder()
                .layoutId(command.getLayoutId())
                .width(command.getWidth())
                .height(command.getHeight())
                .state(SwitchState.ON)
                .type(command.getType())
                .snapshot(command.getSnapshot())
                .createdBy(command.getCreatedBy())
                .createdAt(Timestamp.from(Instant.now()))
                .build());
    }

    @CommandHandler
    private void handle(UpdateLayoutCommand command) {
        if (YesNoEnum.NO.equals(this.getEditable())) {
            LOGGER.error("========>>Layout Aggregate[Id:{}] can't be updated [at {}] because of linked by some feed specifications.",
                    command.getLayoutId().getValue(), command.getUpdatedBy());
            throw new BusinessException(ResponseCodeEnum.BIZ_ERROR, "指定的布局样式不可编辑");
        }
        apply(LayoutUpdatedEvent.builder()
                .layoutId(command.getLayoutId())
                .width(command.getWidth())
                .height(command.getHeight())
                .snapshot(command.getSnapshot())
                .updatedBy(command.getUpdatedBy())
                .updatedAt(Timestamp.from(Instant.now()))
                .build());
    }

    @CommandHandler
    private void handle(ActivateLayoutCommand command) {
        if (this.getState() == command.getState()) {
            return;
        }
        apply(new LayoutActivatedEvent(
                command.getLayoutId(), command.getState(),
                command.getUpdatedBy(), command.getUpdatedAt()
        ));
    }

    @CommandHandler
    private void handle(DeactivateLayoutCommand command) {
        if (this.getState() == command.getState()) {
            return;
        }
        apply(new LayoutDeactivatedEvent(
                command.getLayoutId(), command.getState(),
                command.getUpdatedBy(), command.getUpdatedAt()
        ));
    }

    @EventSourcingHandler
    private void on(LayoutCreatedEvent event) {
        this.setLayoutId(event.getLayoutId());
        this.setWidth(event.getWidth());
        this.setHeight(event.getHeight());
        this.setImageCount(event.getType().getImageCount());
        this.setState(event.getState());
        this.setLayoutType(event.getType());
        this.setSnapshot(event.getSnapshot());
        this.setCreatedBy(event.getCreatedBy());
        this.setUpdatedBy(event.getCreatedBy());
        this.setCreatedAt(event.getCreatedAt());
        this.setUpdatedAt(event.getCreatedAt());

        LOGGER.info("========>>Layout aggregate[Id:{}] is created by User[Id:{}] at {}.",
                event.getLayoutId().getValue(), event.getCreatedBy(), event.getCreatedAt());
    }

    @EventSourcingHandler
    private void on(LayoutUpdatedEvent event) {
        this.setLayoutId(event.getLayoutId());
        this.setWidth(event.getWidth());
        this.setHeight(event.getHeight());
        this.setSnapshot(event.getSnapshot());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdatedAt());

        LOGGER.info("========>>Layout aggregate[Id:{}] is updated by User[Id:{}] at {}.",
                event.getLayoutId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventSourcingHandler
    private void on(LayoutActivatedEvent event) {
        this.setState(event.getState());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdatedAt());

        LOGGER.info("========>>Layout aggregate[Id:{}] is activated by User[Id:{}] at {}.",
                event.getLayoutId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventSourcingHandler
    private void on(LayoutDeactivatedEvent event) {
        this.setState(event.getState());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdatedAt());

        LOGGER.info("========>>Layout aggregate[Id:{}] is deactivated by User[Id:{}] at {}.",
                event.getLayoutId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
