package com.damon.oauth.domain.user.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表
 * @author Damon
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oms_oauth_user")
public class UserEntry {
    @Id
    @NonNull
    @Column(name = "user_id")
    private Long        userId;
    @Column(name = "user_name")
    private String      userName;
    @Column(name = "nick_name")
    private String      nickName;
    @Column private String      password;
    @Column private String      salt;
    @Column(name = "phone_no")
    private String      phoneNo;
    @Column private String      email;
    @Column private String      state;
    @Column private String      type;
    @Column(name = "roles_json")
    private String      rolesJson;
    @Column(name = "tenant_id")
    private Long        tenantId;
    @Column(name = "created_by")
    private Long        createdBy;
    @Column(name = "updated_by")
    private Long        updatedBy;
    @Column(name = "created_at")
    private Long        createdAt;
    @Column(name = "updated_at")
    private Long        updatedAt;
    @Column(name = "last_login_at")
    private Long        lastLoginAt;
}
