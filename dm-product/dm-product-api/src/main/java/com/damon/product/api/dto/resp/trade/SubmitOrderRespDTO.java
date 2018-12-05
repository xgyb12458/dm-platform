package com.damon.product.api.dto.resp.trade;

import com.damon.product.shared.enums.OrderState;
import com.damon.product.shared.enums.OrderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Damon S.
 */
@Data
@ToString
@Builder
@ApiModel(value = "提交订单返回数据")
public class SubmitOrderRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "name", value = "用户名")
    private String name;

    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;

    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    @ApiModelProperty(name = "state", value = "用户状态")
    private OrderState state;

    @ApiModelProperty(name = "type", value = "用户类型")
    private OrderType type;

    @ApiModelProperty(name = "qq", value = "QQ")
    private Long qq;

    @ApiModelProperty(name = "contact", value = "联系地址")
    private String contact;
}
