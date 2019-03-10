package com.damon.bank.api.dto.resp.trade;

import com.damon.bank.shared.enums.OrderState;
import com.damon.bank.shared.enums.OrderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Damon S.
 */
@Data
@Builder
@ApiModel(value = "提交订单返回数据")
public class SubmitOrderRespDTO implements Serializable {
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
