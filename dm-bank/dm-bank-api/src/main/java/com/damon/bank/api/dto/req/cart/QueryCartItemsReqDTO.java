package com.damon.bank.api.dto.req.cart;

import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询购物车中商品参数
 * @author Damon S.
 */
@Data
@ApiModel(value = "购物车商品查询参数")
public class QueryCartItemsReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

}
