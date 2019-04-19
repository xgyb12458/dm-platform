package com.damon.oauth.domain.role.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Damon
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "oms_oauth_role")
public final class RoleEntry {
    @Id
    @NonNull
    @Column(name = "role_id")
    private Long        roleId;
    @Column private String name;
    @Column private String code;
    @Column private String state;
    @Column(name = "perms_json")
    private String      permsJson;
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
