package com.damon.order.api.dto.req.trade;

import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.PayState;
import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询订单信息参数
 * @author Damon S.
 */
@Data
@ApiModel(value = "查询订单")
public class QueryOrdersReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "orderState", value = "订单状态")
    private OrderState orderState;

    @ApiModelProperty(name = "payState", value = "支付状态")
    private PayState payState;
}
