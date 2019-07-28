package com.damon.oauth.api.web.facade;

import com.damon.oauth.manager.dto.req.tenant.CreateTenantReqDTO;
import com.damon.oauth.api.dto.req.user.CreateUserByNameReqDTO;
import com.damon.oauth.api.dto.req.user.ObtainCaptchaReqDTO;
import com.damon.oauth.api.dto.req.user.QueryUserReqDTO;
import com.damon.oauth.api.dto.req.user.UserLoginReqDTO;
import com.damon.oauth.api.dto.resp.user.UserInfoRespDTO;
import com.damon.oauth.api.dto.resp.user.UserLoginRespDTO;
import com.damon.oauth.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户权限管理
 * @author Damon S.
 */
@RequestMapping(value = "/oauth/manager" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface UserFacade {

    /**
     * 修改用户信息
     */
    @PutMapping("/users")
    ResponseWrapper<Boolean> updateUser(
            @RequestBody @Valid CreateUserByNameReqDTO createUserByNameReqDTO
    );

    /**
     * 用户被动注册
     */
    @PostMapping("/users")
    ResponseWrapper<Boolean> createUser(
            @RequestBody @Valid CreateUserByNameReqDTO createUserByNameReqDTO,
            @RequestHeader("tenantId") Long tenantId
    );

    /**
     * 分页查询指定条件的用户
     */
    @GetMapping("/users")
    ResponseWrapper<Pagination<UserInfoRespDTO>> listUsers(
            @RequestBody @Valid QueryUserReqDTO queryUserReqDTO
    );

    /**
     * 用户登录
     */
    @GetMapping("/users/signin")
    ResponseWrapper<UserLoginRespDTO> login(
            @RequestBody @Valid UserLoginReqDTO loginReqDTO
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
            @RequestBody @Valid CreateTenantReqDTO createTenantReqDTO
    );

    /**
     * 获取验证码接口
     */
    @GetMapping("/captcha")
    ResponseWrapper<Boolean> obtainCaptcha(
            @RequestBody @Valid ObtainCaptchaReqDTO obtainCaptchaReqDTO
    );

//    ResponseWrapper<Boolean> ddd(@MatrixVariable("niu") String time, DeferredResult dd,
//                                 HttpHeaders headers, BindingResult validation);
}
