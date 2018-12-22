package com.damon.order.api.dto.req.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 确认订单请求
 * @author Damon S.
 */
@Data
@ApiModel(value = "确认订单参数")
public class ConfirmOrderReqDTO implements Serializable {
    private static final Long serialVersionUID = 12L;

    @ApiModelProperty(name = "cid", value = "购物车项编号")
    private List<Long> cid;

    @ApiModelProperty(name = "skuid", value = "商品SkuId")
    private Long skuid;

    @ApiModelProperty(name = "qty", value = "购买数量")
    private Integer qty;

    @ApiModelProperty(name = "pid", value = "活动编号")
    private Long pid;
}
