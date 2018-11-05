package com.damon.order.api.web.facade;

import com.damon.order.api.dto.req.tenant.CreateTenantReqDTO;
import com.damon.order.api.dto.req.user.CreateUserReqDTO;
import com.damon.order.api.dto.req.user.ObtainCaptchaReqDTO;
import com.damon.order.api.dto.req.user.QueryUserReqDTO;
import com.damon.order.api.dto.req.user.UserLoginReqDTO;
import com.damon.order.api.dto.resp.user.UserInfoRespDTO;
import com.damon.order.api.dto.resp.user.UserLoginRespDTO;
import com.damon.order.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户权限管理
 * @author Damon S.
 */
@RequestMapping(value = "/oauth" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface UserFacade {

    /**
     * 用户主动注册
     */
    @PostMapping("/users/register")
    ResponseWrapper<Boolean> registerUser(
            @RequestBody @Validated CreateUserReqDTO createUserReqDTO
    );

    /**
     * 用户被动注册
     */
    @PostMapping("/users")
    ResponseWrapper<Boolean> createUser(
            @RequestBody @Validated CreateUserReqDTO createUserReqDTO,
            @RequestHeader("tenantId") Long tenantId
    );

    /**
     * 分页查询指定条件的用户
     */
    @GetMapping("/users")
    ResponseWrapper<Pagination<UserInfoRespDTO>> listUsers(
            @RequestBody @Validated QueryUserReqDTO queryUserReqDTO
    );

    /**
     * 用户登录
     */
    @GetMapping("/users/signin")
    ResponseWrapper<UserLoginRespDTO> login(
            @RequestBody @Validated UserLoginReqDTO loginReqDTO
    );

    /**
     * 用户登出
     */
    @GetMapping("/users/signout")
    ResponseWrapper<Boolean> logout(
            @RequestParam("userId") Long userId
    );

    /**
     * 创建租户
     */
    @PostMapping("/tenants")
    ResponseWrapper<Boolean> createTenant(
            @RequestBody @Validated CreateTenantReqDTO createTenantReqDTO
    );

    /**
     * 获取验证码接口
     */
    @GetMapping("/captcha")
    ResponseWrapper<Boolean> obtainCaptcha(
            @RequestBody @Validated ObtainCaptchaReqDTO obtainCaptchaReqDTO
    );
}
