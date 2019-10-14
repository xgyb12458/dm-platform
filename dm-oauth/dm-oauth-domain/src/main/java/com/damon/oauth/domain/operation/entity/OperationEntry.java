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
@Table(name = "t_oauth_operation")
public final class OperationEntry extends TenantEntry {

    @Id
    @NonNull
    @Column(name = "operation_id")
    private Long    operationId;

    @Column private String code;
    @Column private String name;
    @Column private Integer sort;
    @Column private Integer platform;
    @Column private Integer state;

    /**
     * 该操作域与哪些资源域关联
     * 1. 关联至最小一级资源域Id，多个资源域以逗号分隔。
     * 2. 字符串【ALL】表示关联所有资源域
     */
    @Column(name = "res_list")
    private String resList;
}
