package com.damon.order.core.impl;

import com.damon.order.api.dto.req.tenant.CreateTenantReqDTO;
import com.damon.order.api.dto.req.user.CreateUserReqDTO;
import com.damon.order.api.dto.req.user.ObtainCaptchaReqDTO;
import com.damon.order.api.dto.req.user.QueryUserReqDTO;
import com.damon.order.api.dto.req.user.UserLoginReqDTO;
import com.damon.order.api.dto.resp.user.UserInfoRespDTO;
import com.damon.order.api.dto.resp.user.UserLoginRespDTO;
import com.damon.order.api.facade.UserFacade;
import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.order.domain.trade.command.CreateTradeCommand;
import com.damon.shared.common.Pagination;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * @author Damon S.
 */
@RestController
@Api(tags = "用户管理接口")
public class UserFacadeImpl implements UserFacade {
    private final CommandGateway commandGateway;

    public UserFacadeImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "用户注册", notes = "用户主动注册")
    public ResponseWrapper<Boolean> registerUser(CreateUserReqDTO createUserReqDTO) {
        CreateTradeCommand command = CreateTradeCommand.builder()
                .userId(new UserId())
                .userName(createUserReqDTO.getUserName())
                .password(createUserReqDTO.getPassword())
                .captcha(createUserReqDTO.getCaptcha())
                .createdAt(LocalDateTime.now())
                .createdBy(0L)
                .build();
        commandGateway.sendAndWait(command);
        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "用户注册", notes = "用户被动注册")
    public ResponseWrapper<Boolean> createUser(CreateUserReqDTO createUserReqDTO, Long tenantId) {
        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "创建租户", notes = "管理员创建租户（权限限制）")
    public ResponseWrapper<Boolean> createTenant(CreateTenantReqDTO createTenantReqDTO) {
        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "用户列表", notes = "分页查询注册用户，列表显示。")
    public ResponseWrapper<Pagination<UserInfoRespDTO>> listUsers(QueryUserReqDTO queryUserReqDTO) {
        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "获取验证码", notes = "用户注册时获取验证码")
    public ResponseWrapper<Boolean> obtainCaptcha(ObtainCaptchaReqDTO obtainCaptchaReqDTO) {
        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "用户登录", notes = "用户登录（用户名/密码/验证码）。")
    public ResponseWrapper<UserLoginRespDTO> login(UserLoginReqDTO loginReqDTO) {
        UserLoginCommand command = UserLoginCommand.builder()
                .userName(loginReqDTO.getUserName())
                .password(loginReqDTO.getPassword())
                .captcha(loginReqDTO.getCaptcha())
                .build();
        commandGateway.sendAndWait(command);

        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "用户注册", notes = "用户主动注册")
    public ResponseWrapper<Boolean> logout(Long userId) {
        return null;
    }
}
