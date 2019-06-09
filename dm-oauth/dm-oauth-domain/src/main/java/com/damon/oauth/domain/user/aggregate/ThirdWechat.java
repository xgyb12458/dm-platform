package com.damon.oauth.domain.user.aggregate;

import lombok.Data;

/**
 * 第三方微信信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月19日 19:15
 */
@Data
public class ThirdWechat {
    private Long    wechatId;
    private String  userId;
    private String  openId;
    private String  unionId;
    private String  nickName;
    private String  avatarUrl;
    private String  gender;
    private String  province;
    private String  city;
    private String  country;
}
