package com.damon.oauth.domain.role.entity;

import com.damon.oauth.shared.entity.TenantEntry;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "t_pms_role")
public final class RoleEntry extends TenantEntry {
    @Id
    @NonNull
    @Column(name = "role_id")
    private Long    roleId;

    @Column(name = "perms_json")
    private String  permsJson;

    @Column private String name;
    @Column private String code;
    @Column private String platform;
    @Column private String state;
}
