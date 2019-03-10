package com.damon.order.api.dto.req.trade;

import com.damon.shared.dto.SecurityReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 更新购物车中商品请求
 * @author Damon S.
 */
@Data
@ApiModel(value = "更新购物车商品参数")
public class UpdateOrderReqDTO extends SecurityReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车ID")
    private Long cid;

    @ApiModelProperty(value = "已选中")
    private Boolean selected;

    @ApiModelProperty(value = "商品数量")
    private Integer qty;
}
