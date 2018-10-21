package com.damon.oauth.domain.user.entity;

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
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dm_oauth_user")
public final class UserEntry {

    @Id
    @NonNull
    @Column private Long userId;
    @Column private String name;
    @Column private String password;
    @Column private String salt;
    @Column private String phone;
    @Column private String email;
    @Column private String state;
    @Column private String type;
    @Column private String rolesJson;
    @Column private Long tenantId;

    @Column private Long createdBy;
    @Column private Long updatedBy;
    @Column private Timestamp createdAt;
    @Column private Timestamp updatedAt;
    @Column private Timestamp lastLogin;
}
