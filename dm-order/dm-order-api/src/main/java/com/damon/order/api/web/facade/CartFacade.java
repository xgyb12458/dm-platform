package com.damon.order.api.web.facade;

import com.damon.order.api.dto.req.cart.AddItemToCartReqDTO;
import com.damon.order.api.dto.req.cart.QueryCartItemsReqDTO;
import com.damon.order.api.dto.req.cart.UpdateCartItemReqDTO;
import com.damon.order.api.dto.resp.cart.AddItemToCartRespDTO;
import com.damon.order.api.dto.resp.cart.CartItemInfoRespDTO;
import com.damon.order.shared.constant.ApiConstants;
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


    @PutMapping("/cart/items")
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateCartItemReqDTO updateCartItemReqDTO
    );


    @DeleteMapping("/cart/items/{itemId}")
    ResponseWrapper<Boolean> remove(
            @PathVariable("itemId") String itemId
    );
}
