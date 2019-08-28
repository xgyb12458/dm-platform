package com.damon.oauth.domain.resource.aggregate;

import com.damon.shared.enums.SwitchState;
import com.damon.shared.model.ValueObject;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 资源-值对象
 * @author Damon S.
 */
@Value
public class Resource implements ValueObject<Resource>, TenantAware<TenantId> {
    private Long            resourceId;
    private String          code;
    private String          name;
    private String          path;
    private String          sort;
    private String          platform;
    private SwitchState     state;
    private Long            parentId;
    private TenantId        tenantId;
    private Long            createdBy;
    private Long            updatedBy;
    private LocalDateTime   createdAt;
    private LocalDateTime   updatedAt;


    @Override
    public boolean sameAs(Resource o) {
        return !Objects.isNull(o)
                && Objects.equals(getResourceId(), o.getResourceId())
                && Objects.equals(getCode(), o.getCode())
                && Objects.equals(getName(), o.getName())
                && Objects.equals(getPlatform(), o.getPlatform())
                && Objects.equals(getParentId(), o.getParentId())
                && Objects.equals(getTenantId(), o.getTenantId());
    }
}
