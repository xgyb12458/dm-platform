package com.damon.media.core.facade.impl;

import com.damon.media.api.dto.req.NormalLoginReqDTO;
import com.damon.media.api.dto.req.PhoneLoginReqDTO;
import com.damon.media.api.dto.resp.LoginRespDTO;
import com.damon.media.api.web.facade.AuthFacade;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Damon S.
 */
@RestController
@Api(tags = "权限管理")
public class AuthFacadeImpl implements AuthFacade {

    @ArgsValid
    @Override
    @ApiOperation(value = "账户登录", notes = "验证用户登录信息")
    public ResponseWrapper<LoginRespDTO> signInNormally(NormalLoginReqDTO normalLoginReqDTO) {
        return null;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "手机登录", notes = "验证用户登录信息")
    public ResponseWrapper<LoginRespDTO> signInByCaptcha(PhoneLoginReqDTO phoneLoginReqDTO) {
        return null;
    }


    @Override
    @ApiOperation(value = "用户登出", notes = "用户登出")
    public ResponseWrapper<Boolean> logout() {
        return null;
    }
}
