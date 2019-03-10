package com.damon.order.api.dto.resp.cart;

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
@ApiModel(value = "购物车项信息")
public class CartItemInfoRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车项Id")
    private Long cid;

    @ApiModelProperty(value = "商品SkuId")
    private Long skuid;

    @ApiModelProperty(value = "购买数量")
    private Integer qty;

    @ApiModelProperty(value = "活动编号")
    private Long pid;

    @ApiModelProperty(value = "是否已失效")
    private Boolean invalid;
}
