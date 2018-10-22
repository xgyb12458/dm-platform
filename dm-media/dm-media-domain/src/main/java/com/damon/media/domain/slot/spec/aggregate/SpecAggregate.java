package com.damon.media.domain.slot.spec.aggregate;

import com.damon.media.domain.slot.layout.aggregate.LayoutAggregate;
import com.damon.media.domain.slot.spec.command.ActivateSpecCommand;
import com.damon.media.domain.slot.spec.command.CreateSpecCommand;
import com.damon.media.domain.slot.spec.command.DeactivateSpecCommand;
import com.damon.media.domain.slot.spec.command.UpdateSpecCommand;
import com.damon.media.domain.slot.spec.event.SpecActivatedEvent;
import com.damon.media.domain.slot.spec.event.SpecCreatedEvent;
import com.damon.media.domain.slot.spec.event.SpecDeactivatedEvent;
import com.damon.media.domain.slot.spec.event.SpecUpdatedEvent;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.enums.SwitchState;
import com.damon.shared.enums.YesNoEnum;
import com.damon.shared.exception.BusinessException;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.Repository;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * 资源位规格
 * @author Damon S.
 */
@ToString
@Getter
@Setter(value = AccessLevel.PRIVATE)
@org.axonframework.spring.stereotype.Aggregate
@NoArgsConstructor
public class SpecAggregate {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecAggregate.class);

    @AggregateIdentifier
    private SpecId specId;
    private Integer width;
    private Integer height;
    private String imageType;
    private Integer imageSize;
    private String snapshot;
    private SwitchState state;
    private SlotType type;
    private List<Long> layoutIds;
    private SpecExtension ext;
    private Long createdBy;
    private Long updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private List<LayoutAggregate> layoutAggregates;
    @Autowired
    private Repository<LayoutAggregate> layoutRepository;


    public YesNoEnum getEditable() {
        // TODO:
        return YesNoEnum.YES;
    }

    @CommandHandler
    public SpecAggregate(CreateSpecCommand command) {
        Assert.nonNull(command.getHeight(), () -> "高度不能为空");
        Assert.nonNull(command.getWidth(), () -> "宽度不能为空");
        Assert.nonNull(command.getImageType(), () -> "图片类型不能为空");
        Assert.nonNull(command.getImageSize(), () -> "图片大小不能为空");
        Assert.nonNull(command.getSlotType(), () -> "规格类型不能为空");

        if (SlotType.FEEDS.equals(command.getSlotType())
                && command.getLayoutIds().size() < 1) {
            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST, "必须为信息流规格指定布局样式");
        }
        apply(SpecCreatedEvent.builder()
                .specId(command.getSpecId())
                .width(command.getWidth())
                .height(command.getHeight())
                .imageType(command.getImageType())
                .imageSize(command.getImageSize())
                .state(SwitchState.ON)
                .type(command.getSlotType())
                .layoutIds(SlotType.FEEDS.equals(command.getSlotType()) ? command.getLayoutIds() : new ArrayList<>())
                .snapshot(command.getSnapshot())
                .ext(new SpecExtension(command.getFrameCount(), command.getLookAndFeel()))
                .createdBy(command.getCreatedBy())
                .createdAt(command.getCreatedAt())
                .build());
    }

    @CommandHandler
    private void handle(UpdateSpecCommand command) {
        if (YesNoEnum.NO.equals(this.getEditable())) {
            return;
        }
        if (SlotType.FEEDS.equals(this.getType())
                && command.getLayoutIds().size() < 1) {
            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST, "必须为信息流规格指定布局样式");
        }
        apply(SpecUpdatedEvent.builder()
                .specId(command.getSpecId())
                .width(command.getWidth())
                .height(command.getHeight())
                .imageType(command.getImageType())
                .imageSize(command.getImageSize())
                .layoutIds(SlotType.FEEDS.equals(this.getType()) ? command.getLayoutIds() : new ArrayList<>())
                .snapshot(command.getSnapshot())
                .ext(new SpecExtension(command.getFrameCount(), command.getLookAndFeel()))
                .updatedBy(command.getUpdatedBy())
                .updatedAt(command.getUpdatedAt())
                .build());
    }

    @CommandHandler
    private void handle(ActivateSpecCommand command) {
        if (this.getState() == command.getState()) {
            return;
        }
        apply(new SpecActivatedEvent(
                command.getSpecId(), command.getState(),
                command.getUpdatedBy(), command.getUpdateTime()
        ));
    }

    @CommandHandler
    private void handle(DeactivateSpecCommand command) {
        if (this.getState() == command.getState()) {
            return;
        }
        apply(new SpecDeactivatedEvent(
                command.getSpecId(), command.getState(),
                command.getUpdatedBy(), command.getUpdateTime()
        ));
    }

    @EventSourcingHandler
    private void on(SpecCreatedEvent event) {
        this.setSpecId(event.getSpecId());
        this.setWidth(event.getWidth());
        this.setHeight(event.getHeight());
        this.setImageType(event.getImageType());
        this.setImageSize(event.getImageSize());
        this.setState(event.getState());
        this.setType(event.getType());
        this.setLayoutIds(event.getLayoutIds());
        this.setSnapshot(event.getSnapshot());
        this.setExt(event.getExt());
        this.setCreatedBy(event.getCreatedBy());
        this.setCreatedAt(event.getCreatedAt());
        this.setUpdatedBy(event.getCreatedBy());
        this.setUpdatedAt(event.getCreatedAt());
        this.setLayoutAggregates(
                constructLayouts(event.getLayoutIds())
        );

        LOGGER.info("========>>Spec aggregate[Id:{}] is created by User[Id:{}] at {}.",
                event.getSpecId().getValue(), event.getCreatedBy(), event.getCreatedAt());
    }

    @EventSourcingHandler
    private void on(SpecUpdatedEvent event) {
        this.setSpecId(event.getSpecId());
        this.setWidth(event.getWidth());
        this.setHeight(event.getHeight());
        this.setImageType(event.getImageType());
        this.setImageSize(event.getImageSize());
        this.setLayoutIds(event.getLayoutIds());
        this.setSnapshot(event.getSnapshot());
        this.setExt(event.getExt());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdatedAt());
        this.setLayoutAggregates(
                constructLayouts(event.getLayoutIds())
        );

        LOGGER.info("========>>Spec aggregate[Id:{}] is updated by User[Id:{}] at {}.",
                event.getSpecId().getValue(), event.getUpdatedBy(), event.getUpdatedAt());
    }

    @EventSourcingHandler
    private void on(SpecActivatedEvent event) {
        this.setState(event.getState());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdateTime());

        LOGGER.info("========>>Spec aggregate[Id:{}] is activated by User[Id:{}] at {}.",
                event.getSpecId().getValue(), event.getUpdatedBy(), event.getUpdateTime());
    }

    @EventSourcingHandler
    private void on(SpecDeactivatedEvent event) {
        this.setState(event.getState());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdateTime());

        LOGGER.info("========>>Spec aggregate[Id:{}] is deactivated by User[Id:{}] at {}.",
                event.getSpecId().getValue(), event.getUpdatedBy(), event.getUpdateTime());
    }

    private List<LayoutAggregate> constructLayouts(List<Long> layoutIds) {
        final List<LayoutAggregate> layoutAggregates = new ArrayList<>();
        layoutIds.forEach(layoutId -> {
            Aggregate<LayoutAggregate> layoutAggregate = layoutRepository.load(String.valueOf(layoutId));
            if (layoutAggregate.isDeleted()) {
                throw new BusinessException(ResponseCodeEnum.NOT_FOUND, "找不到Id=" + layoutId + "的布局样式数据");
            }
            layoutAggregate.execute(layoutAggregates::add);
        });
        return layoutAggregates;
    }
}
