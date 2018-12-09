package com.damon.ucenter.core.web.facade.impl;

import com.damon.ucenter.api.dto.req.user.CreateUserReqDTO;
import com.damon.ucenter.api.dto.req.user.QueryUserReqDTO;
import com.damon.ucenter.api.dto.req.user.UpdateUserReqDTO;
import com.damon.ucenter.api.dto.resp.user.UserInfoRespDTO;
import com.damon.ucenter.api.dto.resp.user.CreateUserRespDTO;
import com.damon.ucenter.api.web.facade.UserFacade;
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
public class UserFacadeImpl implements UserFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    /**
     * 构造函数注入
     */
    public UserFacadeImpl(CommandGateway commandGateway,
                          QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }


    @Override @ArgsValid
    @ApiOperation(value = "查询购物车", notes = "查询购物车商品")
    public ResponseWrapper<Pagination<CreateUserRespDTO>> list(
            QueryUserReqDTO queryUserReqDTO) {

        List<CreateUserRespDTO> cartItems = new ArrayList<>();
        return new ResponseWrapper<>(
                new Pagination<>(
                        queryUserReqDTO.getPageIndex(),
                        queryUserReqDTO.getPageSize(),
                        50,
                        cartItems)
        );
    }


    @Override @ArgsValid
    @ApiOperation(value = "添加购物车", notes = "将商品添加到购物车")
    public ResponseWrapper<UserInfoRespDTO> add(
            CreateUserReqDTO createUserReqDTO) {
        return null;
    }


    @Override @ArgsValid
    @ApiOperation(value = "删除购物车", notes = "删除购物车中的商品")
    public ResponseWrapper<Boolean> remove(
            UpdateUserReqDTO updateUserReqDTO) {
        return null;
    }
}
