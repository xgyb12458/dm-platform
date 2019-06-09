package com.damon.oauth.api.dto.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 获取验证码
 * @author Damon S.
 */
@Data
@NoArgsConstructor
@ApiModel(value = "获取验证码")
public class ObtainCaptchaReqDTO {

    @ApiModelProperty(value = "手机号/邮箱/用户名", required = true)
    @NotNull(message = "请输入登录账号")
    @Pattern(regexp = "(1\\d{10}|\\w{6,20}|/^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$/)", message = "账号或密码错误")
    private String userName;

}
