package com.damon.oauth.domain.permission.entity;

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
@Table(name = "t_pms_permission")
public final class PermissionEntry extends TenantEntry {

    @Id
    @NonNull
    @Column(name = "permission_id")
    private Long    permissionId;

    @Column(name = "resource_id")
    private Long    resourceId;
    @Column private String platform;
    @Column private String state;

    /**
     * 该权限域对此资源域拥有的所有操作域列表
     */
    @Column(name = "op_list")
    private String  opList  ;
}
