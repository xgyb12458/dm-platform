package com.damon.media.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "手机验证码登录")
public class PhoneLoginReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号", required = true)
    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp = "(\\d\\w\\s*)", message = "手机号码不合法")
    private String phoneNumber;

    @ApiModelProperty(value = "验证码", required = true)
    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "(\\d\\w\\s*)", message = "验证码不合法")
    private String captcha;


    private LocalTime expiredAt;
}
