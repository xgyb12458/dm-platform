package com.damon.oauth.domain.permission.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Damon
 */
@Entity
@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oms_oauth_permission")
public final class PermissionEntry {

    @Id
    @NonNull
    @Column(name = "permission_id")
    private Long        permissionId;
    @Column(name = "resource_id")
    private Long        resourceId;
    @Column(name = "operation_id")
    private Long        operationId;
    @Column private String state;
    @Column(name = "tenant_id")
    private Long        tenantId;

    @Column(name = "created_by")
    private Long        createdBy;
    @Column(name = "updated_by")
    private Long        updatedBy;
    @Column(name = "created_at")
    private Long        createdAt;
    @Column(name = "updated_at")
    private Long        updatedAt;
}
