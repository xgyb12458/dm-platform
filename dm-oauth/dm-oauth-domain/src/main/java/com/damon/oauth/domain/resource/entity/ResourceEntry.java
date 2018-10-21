package com.damon.oauth.domain.resource.entity;

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
@Data
@Builder
@WorkerId(2)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "dm_oauth_resource")
public final class ResourceEntry {

    @Id
    @NonNull
    @Column private Long resourceId;
    @Column private String name;
    @Column private String code;
    @Column private String state;
    @Column private Long parentId;
    @Column private Long tenantId;

    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;
}
