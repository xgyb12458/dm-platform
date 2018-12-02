package com.damon.order.api.dto.req.cart;

import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 向购物车中添加商品请求
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "购物车商品查询参数")
public class QueryCartItemsReqDTO extends PageableReqDTO implements Serializable {
}
