package com.damon.order.api.dto.req.trade;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

/**
 * 确认订单信息请求参数
 * @author Damon S.
 */
@Getter
@ToString
@ApiModel(value = "确认订单所需参数")
public class ConfirmOrderCommand {
}
