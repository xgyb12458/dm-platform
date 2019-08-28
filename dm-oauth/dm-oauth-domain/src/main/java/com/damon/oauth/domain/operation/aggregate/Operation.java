package com.damon.oauth.domain.operation.aggregate;

import com.damon.shared.enums.SwitchState;
import com.damon.shared.model.ValueObject;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 操作域-值对象
 * @author Damon
 */
@Data
public class Operation implements ValueObject<Operation>, TenantAware<TenantId> {
    private Long            operationId;
    private String          code;
    private String          name;
    private String          platform;
    private SwitchState     state;
    private TenantId        tenantId;
    private Long            createdBy;
    private Long            updatedBy;
    private LocalDateTime   createdAt;
    private LocalDateTime   updatedAt;

    @Override
    public boolean sameAs(Operation o) {
        return Objects.equals(getOperationId(), o.getOperationId())
                && Objects.equals(getCode(), o.getCode())
                && Objects.equals(getName(), o.getName())
                && Objects.equals(getPlatform(), o.getPlatform())
                && Objects.equals(getTenantId(), o.getTenantId());
    }
}
