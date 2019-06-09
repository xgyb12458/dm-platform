package com.damon.oauth.domain.user.entity;

import com.damon.oauth.shared.entity.TenantEntry;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "oms_oauth_user")
public class UserEntry extends TenantEntry {
    @Id
    @NonNull
    @Column(name = "user_id")
    private Long    userId;

    @Column(name = "user_name")
    private String  userName;

    @Column(name = "roles_json")
    private String  rolesJson;

    @Column(name = "last_login_at")
    private Long    lastLoginAt;

    @Column private String  phone;
    @Column private String  email;
    @Column private String  password;
    @Column private String  salt;
    @Column private String  state;
    @Column private String  type;
}
