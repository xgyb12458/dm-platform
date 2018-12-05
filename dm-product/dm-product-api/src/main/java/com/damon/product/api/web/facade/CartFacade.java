package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.cart.AddItemToCartReqDTO;
import com.damon.product.api.dto.req.cart.QueryCartItemsReqDTO;
import com.damon.product.api.dto.req.cart.RemoveCartItemReqDTO;
import com.damon.product.api.dto.resp.cart.AddItemToCartRespDTO;
import com.damon.product.api.dto.resp.cart.CartItemInfoRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/trade/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface CartFacade {


    @GetMapping("/cart/items")
    ResponseWrapper<Pagination<CartItemInfoRespDTO>> list(
            @RequestBody @Validated QueryCartItemsReqDTO queryCartItemsReqDTO
    );


    @PostMapping("/cart/items")
    ResponseWrapper<AddItemToCartRespDTO> add(
            @RequestBody @Validated AddItemToCartReqDTO addItemToCartReqDTO
    );


    @DeleteMapping("/cart/items")
    ResponseWrapper<Boolean> remove(
            @RequestBody @Validated RemoveCartItemReqDTO removeCartItemReqDTO
    );
}
