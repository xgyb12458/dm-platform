package com.damon.oauth.domain.operation.entity;

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
@Table(name = "t_pms_operation")
public final class OperationEntry extends AbstractEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operation_id")
    private Long    operationId;

    @Column(name = "tenant_id", nullable = false)
    private Long    tenantId;

    @Column private String code;
    @Column private String name;
    @Column private Integer sort;
    @Column private Integer platform;
    @Column private Integer state;
}
