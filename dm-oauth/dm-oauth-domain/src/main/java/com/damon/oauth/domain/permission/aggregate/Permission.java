package com.damon.oauth.domain.permission.aggregate;

import com.damon.oauth.domain.operation.aggregate.Operation;
import com.damon.oauth.domain.resource.aggregate.Resource;
import com.damon.shared.enums.SwitchState;
import com.damon.shared.model.ValueObject;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Damon
 */
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Aggregate
@NoArgsConstructor
public class Permission implements ValueObject<Permission>, TenantAware<TenantId> {
    @AggregateIdentifier
    private Long            permissionId;
    private Resource        resource;
    private Operation       operation;
    private String          platform;
    private SwitchState     state;
    private TenantId        tenantId;
    private Long            createdBy;
    private Long            updatedBy;
    private LocalDateTime   createdAt;
    private LocalDateTime   updatedAt;


    @Override
    public boolean sameAs(Permission o) {
        return !Objects.isNull(o)
                && Objects.equals(getResource().getResourceId(), o.getResource().getResourceId())
                && Objects.equals(getOperation().getOperationId(), o.getOperation().getOperationId())
                && Objects.equals(getState(), o.getState())
                && Objects.equals(getPlatform(), o.getPlatform())
                && Objects.equals(getTenantId(), o.getTenantId());
    }
}
