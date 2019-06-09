package com.damon.oauth.api.dto.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 通过用户名密码创建用户
 * @author Damon S.
 */
@Data
@NoArgsConstructor
@ApiModel(value = "通过用户名密码创建用户")
public class CreateUserByNameReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "请输入用户名")
    @Pattern(regexp = "^\\w{1,15}$", message = "用户名为字母数字组合且以字母开头")
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    @Pattern(regexp = "", message = "密码为字母数字特殊字符的组合")
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    @NotNull(message = "请输入验证码")
    private String captcha;
}
