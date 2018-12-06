package com.damon.ucenter.core.web.facade.impl;

import com.damon.ucenter.api.dto.req.cart.AddItemToCartReqDTO;
import com.damon.ucenter.api.dto.req.cart.QueryCartItemsReqDTO;
import com.damon.ucenter.api.dto.req.cart.RemoveCartItemReqDTO;
import com.damon.ucenter.api.dto.resp.cart.AddItemToCartRespDTO;
import com.damon.ucenter.api.dto.resp.cart.CartItemInfoRespDTO;
import com.damon.ucenter.api.web.facade.CartFacade;
import com.damon.shared.common.Pagination;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车管理接口
 * @author Damon S.
 */
@Api(tags = "购物车管理接口")
@RestController
public class CartFacadeImpl implements CartFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    /**
     * 构造函数注入
     */
    public CartFacadeImpl(CommandGateway commandGateway,
                           QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }


    @Override @ArgsValid
    @ApiOperation(value = "查询购物车", notes = "查询购物车商品")
    public ResponseWrapper<Pagination<CartItemInfoRespDTO>> list(
            QueryCartItemsReqDTO queryCartItemsReqDTO) {

        List<CartItemInfoRespDTO> cartItems = new ArrayList<>();
        return new ResponseWrapper<>(
                new Pagination<>(
                        queryCartItemsReqDTO.getPageIndex(),
                        queryCartItemsReqDTO.getPageSize(),
                        50,
                        cartItems)
        );
    }


    @Override @ArgsValid
    @ApiOperation(value = "添加购物车", notes = "将商品添加到购物车")
    public ResponseWrapper<AddItemToCartRespDTO> add(
            AddItemToCartReqDTO addItemToCartReqDTO) {
        return null;
    }


    @Override @ArgsValid
    @ApiOperation(value = "删除购物车", notes = "删除购物车中的商品")
    public ResponseWrapper<Boolean> remove(
            RemoveCartItemReqDTO removeCartItemReqDTO) {
        return null;
    }
}
