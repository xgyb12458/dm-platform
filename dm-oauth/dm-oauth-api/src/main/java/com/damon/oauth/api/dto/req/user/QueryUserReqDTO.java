package com.damon.oauth.api.dto.req.user;

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

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户状态")
    private OrderState state;

    @ApiModelProperty(value = "用户类型")
    private OrderType type;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "注册时间")
    private Long registerAt;

    @ApiModelProperty(value = "最近登录时间")
    private Long lastLoginAt;
}
