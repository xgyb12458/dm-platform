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

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 许可域定义
 * @author Damon
 */
@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Permission implements ValueObject, TenantAware<TenantId> {
    private Long            permissionId;
    private Resource        resource;
    private Operation       operation;
    private String          platform;
    private SwitchState     state;
    private TenantId        tenantId;

    private Long            createdBy;
    private Long            updatedBy;
    private Long            removedBy;
    private LocalDateTime   createdAt;
    private LocalDateTime   updatedAt;
    private LocalDateTime   removedAt;


    @Override
    public boolean equals(Object o) {
        Permission permission = null;
        if (o instanceof Permission) {
            permission = (Permission) o;
        }
        return Objects.nonNull(permission)
                && Objects.equals(getResource(), permission.getResource())
                && Objects.equals(getOperation(), permission.getOperation())
                && Objects.equals(getPlatform(), permission.getPlatform())
                && Objects.equals(getState(), permission.getState())
                && Objects.equals(getTenantId(), permission.getTenantId());
    }
}
