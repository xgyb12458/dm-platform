package com.damon.oauth.domain.role.aggregate;

import com.damon.oauth.domain.permission.aggregate.Permission;
import com.damon.shared.enums.SwitchState;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.*;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色聚合
 * @author Damon
 */
@ToString
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
    private List<Permission> permissions;
    private TenantId tenantId;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
