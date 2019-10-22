package com.damon.oauth.shared.entity;

import com.damon.shared.entity.AbstractEntry;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 租户基础字段
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月05日 18:41
 */
@Data
@MappedSuperclass
public abstract class TenantEntry extends AbstractEntry {

    @Column(name = "tenant_id", nullable = false)
    protected Long    tenantId;
}

