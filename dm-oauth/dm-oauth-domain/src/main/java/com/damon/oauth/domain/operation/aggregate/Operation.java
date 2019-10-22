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
public class Operation implements ValueObject, TenantAware<TenantId> {
    private Long            operationId;
    private String          code;
    private String          name;
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
        Operation op = null;
        if (o instanceof Operation) {
            op = (Operation) o;
        }
        return Objects.nonNull(op)
                && Objects.equals(getCode(), op.getCode())
                && Objects.equals(getName(), op.getName())
                && Objects.equals(getState(), op.getState())
                && Objects.equals(getPlatform(), op.getPlatform())
                && Objects.equals(getTenantId(), op.getTenantId());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        // TODO: complete function
        return new StringBuilder().append("").toString();
    }
}
