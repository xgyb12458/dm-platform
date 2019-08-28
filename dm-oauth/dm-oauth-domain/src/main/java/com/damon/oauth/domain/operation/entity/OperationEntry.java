package com.damon.oauth.domain.operation.entity;

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
@Table(name = "oms_oauth_operation")
public final class OperationEntry extends TenantEntry {

    @Id
    @NonNull
    @Column(name = "operation_id")
    private Long    operationId;

    @Column private String code;
    @Column private String name;
    @Column private String platform;
    @Column private String state;
}
