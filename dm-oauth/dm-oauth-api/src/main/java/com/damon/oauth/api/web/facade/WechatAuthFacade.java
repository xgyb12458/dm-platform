package com.damon.oauth.api.web.facade;

import com.damon.oauth.api.dto.req.tenant.CreateTenantReqDTO;
import com.damon.order.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基于微信的权限认证
 * @author Damon S.
 */
@RequestMapping(value = "/oauth" + ApiConstants.API_V1 + "/wechat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface WechatAuthFacade {

    @PostMapping("/tenants")
    ResponseWrapper<Boolean> createTenant(
            @RequestBody @Validated CreateTenantReqDTO createTenantReqDTO
    );
}
