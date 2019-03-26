package com.damon.oauth.domain.permission.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author Damon
 */
@Entity
@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dm_oauth_permission")
public final class PermissionEntry {

    @Id
    @NonNull
    @Column private Long permissionId;
    @Column private Long resourceId;
    @Column private Long operationId;
    @Column private String state;
    @Column private Long tenantId;

    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createAt;
    @Column private Timestamp updateAt;
}
