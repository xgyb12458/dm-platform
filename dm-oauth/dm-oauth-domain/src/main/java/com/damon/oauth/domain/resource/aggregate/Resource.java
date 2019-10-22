package com.damon.oauth.domain.resource.aggregate;

import com.damon.shared.enums.SwitchState;
import com.damon.shared.enums.YesNoEnum;
import com.damon.shared.model.ValueObject;
import com.damon.shared.tenant.TenantAware;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 资源-值对象
 * @author Damon S.
 */
@Value
@Builder
public class Resource implements ValueObject, TenantAware<TenantId> {
    private Long            resourceId;
    private String          code;
    private String          name;
    private String          path;
    private String          platform;
    private SwitchState     state;
    private YesNoEnum       removed;
    private Long            parentId;
    private TenantId        tenantId;

    private Long            createdBy;
    private Long            updatedBy;
    private Long            removedBy;
    private LocalDateTime   createdAt;
    private LocalDateTime   updatedAt;
    private LocalDateTime   removedAt;


    @Override
    public boolean equals(Object o) {
        Resource res = null;
        if (o instanceof Resource) {
            res = (Resource) o;
        }
        return Objects.nonNull(res)
                && Objects.equals(getCode(), res.getCode())
                && Objects.equals(getName(), res.getName())
                && Objects.equals(getPath(), res.getPath())
                && Objects.equals(getState(), res.getState())
                && Objects.equals(getPlatform(), res.getPlatform())
                && Objects.equals(getParentId(), res.getParentId())
                && Objects.equals(getTenantId(), res.getTenantId());
    }
}
