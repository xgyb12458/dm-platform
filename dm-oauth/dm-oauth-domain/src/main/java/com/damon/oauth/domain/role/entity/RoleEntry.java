package com.damon.oauth.domain.role.entity;

import com.damon.shared.common.WorkerId;
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
@Builder
@Data
@WorkerId(11)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "t_rbac_role")
public final class RoleEntry {

    @Id
    @NonNull
    @Column private Long roleId;
    @Column private String name;
    @Column private String code;
    @Column private String state;
    @Column private String permsJson;
    @Column private Long tenantId;

    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;
}
