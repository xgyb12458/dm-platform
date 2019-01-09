package com.damon.bank.api.dto.req.trade;

import com.damon.bank.shared.enums.OrderState;
import com.damon.bank.shared.enums.PaidState;
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

    @ApiModelProperty(name = "paidState", value = "支付状态")
    private PaidState paidState;
}
