package com.damon.oauth.domain.audit.entity;

import com.damon.oauth.shared.entity.TenantEntry;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 操作日志表
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年06月09日 18:34
 */
@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "oms_oauth_operate")
public class AuditEntry extends TenantEntry {

    @Column(name = "operate_id")
    private Long operateId;
    @Column(name = "user_id")
    private String userId;
    @Column private String operate;
    @Column private Long time;
    @Column private String ip;
    @Column private String params;
    @Column private String uri;
    @Column private String type;
}