package com.damon.oauth.domain.role.aggregate;

import com.damon.oauth.domain.permission.aggregate.Permission;
import com.damon.oauth.domain.role.command.CreateRoleCommand;
import com.damon.oauth.domain.role.command.UpdateRoleCommand;
import com.damon.oauth.domain.role.event.RoleCreatedEvent;
import com.damon.oauth.domain.role.event.RoleUpdatedEvent;
import com.damon.shared.common.Constants;
import com.damon.shared.enums.SwitchState;
import com.damon.shared.enums.YesNoEnum;
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
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * 角色聚合
 * @author Damon
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class RoleAggregate implements TenantAware<TenantId> {

    @AggregateIdentifier
    private RoleId roleId;
    private String code;
    private String name;
    private String platform;
    private SwitchState state;
    private YesNoEnum removed;
    private List<Permission> permissions;
    private TenantId tenantId;

    private Long createdBy;
    private Long updatedBy;
    private Long removedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime removedAt;


    @CommandHandler
    public RoleAggregate(CreateRoleCommand command) {
        log.info(Constants.PREFIX_PMS + "==========>>creating role aggregate command, parameters: {}", command.toString());

        apply(RoleCreatedEvent.builder()
                .roleId(new RoleId())
                .code(command.getCode())
                .name(command.getName())
                .platform(command.getPlatform())
                .permissions(command.getPermissions())
                .tenantId(command.getTenantId())
                .createdBy(command.getCreatedBy())
                .createdAt(LocalDateTime.now())
                .build());
    }

    @SuppressWarnings("UnusedDeclaration")
    @CommandHandler
    private void handle(UpdateRoleCommand command) {
        log.info(Constants.PREFIX_PMS + "==========>>updating role aggregate command, parameters: {}", command.toString());

        apply(RoleUpdatedEvent.builder()
                .roleId(command.getRoleId())
                .name(command.getName())
                .permissions(command.getPermissions())
                .updatedBy(command.getUpdatedBy())
                .updatedAt(LocalDateTime.now())
                .build());
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(RoleCreatedEvent event) {
        log.info(Constants.PREFIX_PMS + "==========>>creating role aggregate event, parameters: {}", event.toString());

        setRoleId(event.getRoleId());
        setCode(event.getCode());
        setName(event.getName());
        setPlatform(event.getPlatform());
        setState(SwitchState.ON);
        setRemoved(YesNoEnum.Y);
//        setPermissions(event.getPermissions());
        setTenantId(event.getTenantId());
        setCreatedBy(event.getCreatedBy());
        setCreatedAt(event.getCreatedAt());
    }

    @SuppressWarnings("UnusedDeclaration")
    @EventSourcingHandler
    private void on(RoleUpdatedEvent event) {
        log.info(Constants.PREFIX_PMS + "==========>>updating role aggregate event, parameters: {}", event.toString());

        setName(event.getName());
//        setPermissions(event.getPermissions());
        setUpdatedBy(event.getUpdatedBy());
        setUpdatedAt(event.getUpdatedAt());
    }
}
