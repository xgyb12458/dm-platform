package com.damon.oauth.core.web.manager.facade.impl;

import com.damon.oauth.manager.dto.req.tenant.CreateTenantReqDTO;
import com.damon.oauth.api.dto.req.user.CreateUserByNameReqDTO;
import com.damon.oauth.api.dto.req.user.ObtainCaptchaReqDTO;
import com.damon.oauth.api.dto.req.user.QueryUserReqDTO;
import com.damon.oauth.api.dto.req.user.UserLoginReqDTO;
import com.damon.oauth.api.dto.resp.user.UserInfoRespDTO;
import com.damon.oauth.api.dto.resp.user.UserLoginRespDTO;
import com.damon.oauth.api.web.facade.UserFacade;
import com.damon.oauth.domain.user.aggregate.UserId;
import com.damon.oauth.domain.user.command.CreateUserByNameCommand;
import com.damon.shared.common.Pagination;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * 用户接口
 * @author Damon S.
 */
@RequiredArgsConstructor
@RestController
@EnableSwagger2
@Api(tags = "用户管理接口")
public class UserFacadeImpl implements UserFacade {

    private final CommandGateway commandGateway;


    @ArgsValid
    @Override
    @ApiOperation(value = "用户注册", notes = "用户主动注册")
    public ResponseWrapper<Boolean> registerUser(CreateUserByNameReqDTO createUserByNameReqDTO) {
        CreateUserByNameCommand command = CreateUserByNameCommand.builder()
                .userId(new UserId())
                .userName(createUserByNameReqDTO.getUserName())
                .password(createUserByNameReqDTO.getPassword())
                .createdAt(LocalDateTime.now())
                .createdBy(0L)
                .build();
        UserId userId = commandGateway.sendAndWait(command);
        return new ResponseWrapper<>(Objects.nonNull(userId));
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "用户注册", notes = "用户被动注册", httpMethod = "POST")
    public ResponseWrapper<Boolean> createUser(CreateUserByNameReqDTO createUserByNameReqDTO, Long tenantId) {
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
//        UserLoginCommand command = UserLoginCommand.builder()
//                .userName(loginReqDTO.getUserName())
//                .password(loginReqDTO.getPassword())
//                .captcha(loginReqDTO.getCaptcha())
//                .build();
//        commandGateway.sendAndWait(command);

        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "用户注册", notes = "用户主动注册")
    public ResponseWrapper<Boolean> logout(Long userId) {
        return null;
    }
}
