package com.damon.order.api.dto.resp.cart;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 购物车项添加成功返回结果信息
 * @author Damon S.
 */
@Data
@ToString
@Builder
@ApiModel(value = "购物车项添加成功返回结果信息")
public class AddItemToCartRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
}
