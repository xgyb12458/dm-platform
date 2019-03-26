package com.damon.oauth.domain.operation.entity;

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
@Table(name = "dm_oauth_operation")
public final class OperationEntry {

    @Id
    @NonNull
    @Column private Long operationId;
    @Column private String name;
    @Column private String code;
    @Column private String state;
    @Column private Long tenantId;

    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;
}
