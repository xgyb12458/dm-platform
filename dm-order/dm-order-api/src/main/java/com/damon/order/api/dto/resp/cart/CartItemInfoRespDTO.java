package com.damon.order.api.dto.resp.cart;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
@ApiModel(value = "购物车项信息")
public class CartItemInfoRespDTO implements Serializable {
}
