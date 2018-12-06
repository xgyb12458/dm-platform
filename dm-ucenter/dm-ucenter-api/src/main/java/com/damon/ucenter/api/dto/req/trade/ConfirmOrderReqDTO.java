package com.damon.ucenter.api.dto.req.trade;

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

    @ApiModelProperty(name = "cids", value = "购物车项编号")
    private List<Long> cids;

    @ApiModelProperty(name = "skuid", value = "商品SkuId")
    private Long skuid;

    @ApiModelProperty(name = "qty", value = "购买数量")
    private Integer qty;

    @ApiModelProperty(name = "pid", value = "活动编号")
    private Long pid;

    @ApiModelProperty(name = "did", value = "活动详情编号")
    private Long did;
}
