package com.damon.oauth.domain.permission.aggregate;

import com.damon.oauth.domain.operation.entity.Operation;
import com.damon.oauth.domain.resource.entity.Resource;
import com.damon.shared.enums.SwitchState;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.*;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;

/**
 * @author Damon
 */
@ToString
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class PermissionAggregate implements TenantAware<TenantId> {
    @AggregateIdentifier
    private PermissionId permissionId;
    private Resource resource;
    private Operation operation;
    private SwitchState state;
    private TenantId tenantId;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
