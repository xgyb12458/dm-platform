package com.damon.oauth.domain.user.entity;

import com.damon.oauth.shared.entity.TenantEntry;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户微信信息记录项
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月10日 18:40
 */
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "t_pms_wechat")
public class WechatEntry extends TenantEntry {
    @Id
    @NonNull
    @Column(name = "wechat_id")
    private Long    wechatId;

    @Column(name = "user_id", nullable = false)
    private String  userId;

    @Column(name = "open_id", nullable = false)
    private String  openId;

    @Column(name = "union_id", nullable = false)
    private String  unionId;

    @Column(name = "nick_name")
    private String  nickName;

    @Column(name = "avatar_url")
    private String  avatarUrl;

    @Column private String gender;
    @Column private String province;
    @Column private String city;
    @Column private String country;
}
