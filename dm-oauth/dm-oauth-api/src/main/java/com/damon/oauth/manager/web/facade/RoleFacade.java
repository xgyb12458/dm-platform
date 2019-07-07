package com.damon.oauth.manager.web.facade;

import com.damon.oauth.manager.dto.req.role.CreateRoleReqDTO;
import com.damon.oauth.manager.dto.req.tenant.CreateTenantReqDTO;
import com.damon.oauth.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户权限管理
 * @author Damon S.
 */
@RequestMapping(value = "/oauth" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface RoleFacade {


    @PostMapping("/roles")
    ResponseWrapper<Boolean> createRole(
            @RequestBody @Validated CreateRoleReqDTO createRoleReqDTO,
            @RequestHeader("tenantId") Long tenantId
    );

    @PostMapping("/tenants")
    ResponseWrapper<Boolean> createTenant(
            @RequestBody @Validated CreateTenantReqDTO createTenantReqDTO
    );
}
