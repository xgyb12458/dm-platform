package com.damon.order.api.dto.req.cart;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 删除购物车中的商品请求
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "产出购物车项参数")
public final class RemoveCartItemReqDTO implements Serializable {
}
