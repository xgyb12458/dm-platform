package com.damon.oauth.domain.resource.aggregate;

import com.damon.shared.enums.SwitchState;
import com.damon.shared.model.ValueObject;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Damon
 */
@Data
@ToString
public class Resource implements ValueObject<Resource>, TenantAware<TenantId> {
    private Long resourceId;
    private String code;
    private String name;
    private SwitchState state;
    private Long parentId;
    private TenantId tenantId;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean sameAs(Resource o) {
        return Objects.equals(getCode(), o.getCode())
                && Objects.equals(getName(), o.getName())
                && Objects.equals(getParentId(), o.getParentId())
                && Objects.equals(getTenantId(), o.getTenantId());
    }
}
