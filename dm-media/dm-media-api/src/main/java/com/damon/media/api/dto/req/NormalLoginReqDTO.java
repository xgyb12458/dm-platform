package com.damon.media.api.dto.req;

import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.exception.SystemException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "用户登录参数")
public class NormalLoginReqDTO implements Serializable {
    private static final long serialVersionUID = 15900157076L;

    @ApiModelProperty(value = "用户名/邮箱/手机号", required = true)
    @NotNull(message = "登录名称不能为空")
    @Pattern(regexp = "(\\d\\w\\s*)", message = "登录名称不存在")
    private String name;

    @ApiModelProperty(value = "登录密码", required = true)
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    @NotNull(message = "验证码不能为空")
    private String captcha;


    private void readObject(ObjectInputStream input) {
        try {
            input.defaultReadObject();

            // TODO: 解密密码，使用SealedObject或SignedObject加解密
            this.setPassword(String.valueOf(password));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SystemException(ResponseCodeEnum.BAD_REQUEST.getCode(), "密码解密失败");
        }
    }
}
