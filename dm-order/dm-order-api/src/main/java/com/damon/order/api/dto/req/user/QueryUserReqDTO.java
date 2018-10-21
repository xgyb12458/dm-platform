package com.damon.order.api.dto.req.user;

import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.OrderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户登录请求
 * @author Damon S.
 */
@Data
@ToString
@NoArgsConstructor
@ApiModel(value = "用户查询请求")
public class QueryUserReqDTO {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "state", value = "用户状态")
    private OrderState state;

    @ApiModelProperty(name = "type", value = "用户类型")
    private OrderType type;

    @ApiModelProperty(name = "email", value = "邮箱地址")
    private String email;

    @ApiModelProperty(name = "phone", value = "手机号码")
    private String phone;

    @ApiModelProperty(name = "registerAt", value = "注册时间")
    private Long registerAt;

    @ApiModelProperty(name = "lastLoginAt", value = "最近登录时间")
    private Long lastLoginAt;
}
