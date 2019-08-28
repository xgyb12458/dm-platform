package com.damon.oauth.domain.resource.entity;

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
@Table(name = "oms_oauth_resource")
public final class ResourceEntry extends TenantEntry {

    @Id
    @NonNull
    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column private String name;
    @Column private String code;
    @Column private String path;
    @Column private Integer sort;
    @Column private String platform;
    @Column private String state;
}
