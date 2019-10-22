package com.damon.oauth.domain.resource.entity;

import com.damon.shared.entity.AbstractEntry;
import lombok.*;

import javax.persistence.*;

/**
 * @author Damon
 */
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "t_pms_resource")
public final class ResourceEntry extends AbstractEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "tenant_id", nullable = false)
    private Long    tenantId;

    @Column private String name;
    @Column private String code;
    @Column private String path;
    @Column private Integer sort;
    @Column private Integer platform;
    @Column private Integer state;
}
