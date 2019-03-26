package com.damon.oauth.domain.user.aggregate;

import com.damon.oauth.domain.role.aggregate.RoleAggregate;
import com.damon.oauth.domain.user.command.CreateUserCommand;
import com.damon.oauth.domain.user.command.UpdateUserCommand;
import com.damon.oauth.domain.user.event.UserCreatedEvent;
import com.damon.oauth.domain.user.event.UserUpdatedEvent;
import com.damon.oauth.shared.enums.UserState;
import com.damon.oauth.shared.enums.UserType;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * 用户聚合
 * @author Damon
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class UserAggregate implements TenantAware<TenantId> {
    @AggregateIdentifier
    private UserId          userId;
    private String          userName;
    private String          nickName;
    private String          password;
    private String          phoneNo;
    private String          email;
    private String          salt;
    private UserState       state;
    private UserType        type;
    private Collection<RoleAggregate> roles;
    private TenantId        tenantId;
    private Long            createdBy;
    private Long            updatedBy;
    private LocalDateTime   createdAt;
    private LocalDateTime   updatedAt;
    private LocalDateTime   lastLoginAt;


    @CommandHandler
    public UserAggregate(CreateUserCommand command) {
        // TODO: 检查name+type是否重复，重复则抛出异常
        apply(UserCreatedEvent.builder()
                .userId(command.getUserId())
                .userName(command.getUserName())
                .nickName(command.getNickName())
                .password(command.getPassword())
                .phoneNo(command.getPhoneNo())
                .email(command.getEmail())
                .captcha(command.getCaptcha())
                .type(command.getType())
                .state(UserState.ACTIVE)
                .salt("")
                .tenantId(command.getTenantId())
                .createdBy(command.getCreatedBy())
                .createdAt(LocalDateTime.now())
                .build());
    }

    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(UpdateUserCommand command) {

        apply(UserUpdatedEvent.builder()

                .build());
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(UserCreatedEvent event) {
        this.setUserId(event.getUserId());
        this.setUserName(event.getUserName());
        this.setNickName(event.getNickName());
        this.setPassword(event.getPassword());
        this.setPhoneNo(event.getPhoneNo());
        this.setEmail(event.getEmail());
        this.setType(event.getType());
        this.setState(event.getState());
        this.setSalt(event.getSalt());
        this.setTenantId(event.getTenantId());
        this.setCreatedBy(event.getCreatedBy());
        this.setCreatedAt(event.getCreatedAt());

        log.info("========>>User aggregate[Id:{}, name:'{}'] is created by User[Id:{}] at {}.",
                event.getUserId().getValue(), event.getUserName(), event.getCreatedBy(), event.getCreatedAt());
    }


    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(UserUpdatedEvent event) {
        this.setNickName(event.getNickName());
        this.setPhoneNo(event.getPhoneNo());
        this.setEmail(event.getEmail());
//        this.setType(event.getType());
//        this.setState(event.getState());
//        this.setSalt(event.getSalt());
        this.setTenantId(event.getTenantId());
        this.setUpdatedBy(event.getUpdatedBy());
        this.setUpdatedAt(event.getUpdatedAt());

        log.info("========>>User aggregate[Id:{}, name:'{}'] is updated by User[Id:{}] at {}.",
                event.getUserId().getValue(), getUserName(), event.getUpdatedBy(), event.getUpdatedAt());
    }
}
