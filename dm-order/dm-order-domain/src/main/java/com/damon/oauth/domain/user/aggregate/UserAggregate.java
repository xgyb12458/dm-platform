package com.damon.oauth.domain.user.aggregate;

import com.damon.order.domain.trade.command.SubmitOrderCommand;
import com.damon.order.domain.trade.event.TradeCreatedEvent;
import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.OrderType;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author Damon
 */
@ToString
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class UserAggregate implements TenantAware<TenantId> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAggregate.class);

    @AggregateIdentifier
    private UserId userId;
    private String userName;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private OrderType type;
    private OrderState state;
    private TenantId tenantId;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @CommandHandler
    public UserAggregate(SubmitOrderCommand command) {
        // TODO: 检查name+type是否重复，重复则抛出异常
        apply(TradeCreatedEvent.builder()
//                .tradeId(command.getTenantId())
                .userName(command.getUserName())
                .password(command.getPassword())
                .type(OrderType.NORMAL)
                .salt("")
                .tenantId(command.getTenantId())
                .createdBy(command.getCreatedBy())
                .createdAt(command.getCreatedAt())
                .build());
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(TradeCreatedEvent event) {
//        this.setUserId(event.getUserId());
        this.setUserName(event.getUserName());
        this.setPassword(event.getPassword());
        this.setSalt(event.getSalt());
        this.setType(event.getType());
        this.setState(event.getState());
        this.setCreatedBy(event.getCreatedBy());
        this.setCreatedAt(event.getCreatedAt());

//        LOGGER.info("========>>User aggregate[Idd:{}, name:'{}'] is created by User[Id:{}] at {}.",
//                event.getUserId().getValue(), event.getUserName(), event.getCreatedBy(), event.getCreatedAt());
    }
}
