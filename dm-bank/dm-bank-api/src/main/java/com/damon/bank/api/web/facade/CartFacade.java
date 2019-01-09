package com.damon.bank.api.web.facade;

import com.damon.bank.api.dto.req.cart.AddSkuToCartReqDTO;
import com.damon.bank.api.dto.req.cart.QueryCartItemsReqDTO;
import com.damon.bank.api.dto.req.cart.UpdateCartItemReqDTO;
import com.damon.bank.api.dto.resp.cart.AddSkuToCartRespDTO;
import com.damon.bank.api.dto.resp.cart.CartItemInfoRespDTO;
import com.damon.bank.shared.constant.ApiConstants;
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
    ResponseWrapper<AddSkuToCartRespDTO> add(
            @RequestBody @Validated AddSkuToCartReqDTO addSkuToCartReqDTO
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
