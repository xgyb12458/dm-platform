package com.damon.order.api.dto.req.trade;

import com.damon.shared.dto.SecurityReqDTO;
import io.swagger.annotations.ApiModel;
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

    private Long cid;
    private Boolean selected;
    private Integer qty;
}
