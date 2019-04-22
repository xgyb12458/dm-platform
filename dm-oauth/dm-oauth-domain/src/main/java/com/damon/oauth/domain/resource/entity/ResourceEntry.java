package com.damon.oauth.domain.resource.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "oms_oauth_resource")
public final class ResourceEntry {

    @Id
    @NonNull
    @Column(name = "resource_id")
    private Long resourceId;
    @Column private String name;
    @Column private String code;
    @Column private String state;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "updated_by")
    private Long updatedBy;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;
}
