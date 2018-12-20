package com.damon.order.api.dto.req.cart;

import com.damon.shared.dto.SecurityReqDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 更新购物车中商品请求
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "更新购物车商品参数")
public class UpdateCartItemReqDTO extends SecurityReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long cartItemId;
    private Integer amount;
}
