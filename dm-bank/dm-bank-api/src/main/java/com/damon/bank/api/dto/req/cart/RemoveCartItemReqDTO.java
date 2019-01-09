package com.damon.bank.api.dto.req.cart;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 删除购物车中的商品请求
 * @author Damon S.
 */
@Data
@ApiModel(value = "产出购物车项参数")
public class RemoveCartItemReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long cid;
}
