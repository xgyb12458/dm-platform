package com.damon.oauth.domain.user.aggregate;

import com.damon.oauth.domain.role.aggregate.RoleAggregate;
import com.damon.oauth.domain.user.command.CreateUserCommand;
import com.damon.oauth.domain.user.event.UserCreatedEvent;
import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.OrderType;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

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
    private Collection<RoleAggregate> roles;
    private OrderType type;
    private OrderState state;
    private TenantId tenantId;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @CommandHandler
    public UserAggregate(CreateUserCommand command) {
        // TODO: 检查name+type是否重复，重复则抛出异常
        apply(UserCreatedEvent.builder()
                .userId(command.getUserId())
                .userName(command.getUserName())
                .password(command.getPassword())
                .type(OrderType.NORMAL)
                .state(OrderState.ACTIVE)
                .salt("")
                .tenantId(command.getTenantId())
                .createdBy(command.getCreatedBy())
                .createdAt(command.getCreatedAt())
                .build());
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(UserCreatedEvent event) {
        this.setUserId(event.getUserId());
        this.setUserName(event.getUserName());
        this.setPassword(event.getPassword());
        this.setSalt(event.getSalt());
        this.setType(event.getType());
        this.setState(event.getState());
        this.setCreatedBy(event.getCreatedBy());
        this.setCreatedAt(event.getCreatedAt());

        LOGGER.info("========>>User aggregate[Id:{}, name:'{}'] is created by User[Id:{}] at {}.",
                event.getUserId().getValue(), event.getUserName(), event.getCreatedBy(), event.getCreatedAt());
    }
}
