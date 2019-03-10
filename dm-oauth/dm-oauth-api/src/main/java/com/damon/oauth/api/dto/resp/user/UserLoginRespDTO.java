package com.damon.oauth.api.dto.resp.user;

import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.OrderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "用户登录成功返回信息")
public class UserLoginRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户状态")
    private OrderState state;

    @ApiModelProperty(value = "用户类型")
    private OrderType type;

    @ApiModelProperty(value = "QQ")
    private Long qq;

    @ApiModelProperty(value = "联系地址")
    private String contact;
}
