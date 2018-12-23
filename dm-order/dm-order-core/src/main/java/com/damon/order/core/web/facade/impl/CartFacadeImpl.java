package com.damon.order.core.web.facade.impl;

import com.damon.order.api.dto.req.cart.AddSkuToCartReqDTO;
import com.damon.order.api.dto.req.cart.QueryCartItemsReqDTO;
import com.damon.order.api.dto.req.cart.UpdateCartItemReqDTO;
import com.damon.order.api.dto.resp.cart.AddSkuToCartRespDTO;
import com.damon.order.api.dto.resp.cart.CartItemInfoRespDTO;
import com.damon.order.api.web.facade.CartFacade;
import com.damon.order.domain.cart.command.AddSkuToCartCommand;
import com.damon.order.domain.cart.command.QueryCartItemCommand;
import com.damon.shared.common.Pagination;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;


    @Override @ArgsValid
    @ApiOperation(value = "查询购物车", notes = "查询购物车商品")
    public ResponseWrapper<Pagination<CartItemInfoRespDTO>> list(
            QueryCartItemsReqDTO queryCartItemsReqDTO) {
        QueryCartItemCommand command = QueryCartItemCommand.builder().build();

        queryGateway.query(command, CartItemInfoRespDTO.class);
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
    public ResponseWrapper<AddSkuToCartRespDTO> add(
            AddSkuToCartReqDTO addSkuToCartReqDTO) {
        AddSkuToCartCommand command = AddSkuToCartCommand.builder().build();

        commandGateway.send(command);
        return null;
    }


    @Override @ArgsValid
    @ApiOperation(value = "更新购物车", notes = "更新购物车商品的数量")
    public ResponseWrapper<Boolean> update(UpdateCartItemReqDTO updateCartItemReqDTO) {
        return null;
    }


    @Override @ArgsValid
    @ApiOperation(value = "删除购物车", notes = "删除购物车中的商品")
    public ResponseWrapper<Boolean> remove(String itemId) {
        return null;
    }
}
