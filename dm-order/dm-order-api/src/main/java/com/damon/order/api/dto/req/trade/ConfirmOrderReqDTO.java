package com.damon.order.api.dto.req.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 确认订单请求
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "确认订单参数")
public class ConfirmOrderReqDTO implements Serializable {
    private static final Long serialVersionUID = 12L;

    @ApiModelProperty(name = "cartItemIds", value = "购物车下单")
    private List<Long> cids;

    @ApiModelProperty(name = "skuId", value = "商品SkuId")
    private Long skuid;

    @ApiModelProperty(name = "quantity", value = "购买数量")
    private Integer qty;

    @ApiModelProperty(name = "promotionId", value = "活动编号")
    private Long pid;

    @ApiModelProperty(name = "detailId", value = "活动详情编号")
    private Long did;
}
