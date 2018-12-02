package com.damon.order.api.dto.req.cart;

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
@ApiModel(value = "添加购物车参数")
public final class AddItemToCartReqDTO implements Serializable {
}
