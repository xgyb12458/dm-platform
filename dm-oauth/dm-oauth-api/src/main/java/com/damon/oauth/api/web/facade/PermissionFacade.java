package com.damon.oauth.api.web.facade;

import com.damon.oauth.api.dto.req.operation.CreateOperationReqDTO;
import com.damon.oauth.api.dto.req.permission.CreatePermissionReqDTO;
import com.damon.oauth.api.dto.req.resource.CreateResourceReqDTO;
import com.damon.oauth.api.dto.req.tenant.CreateTenantReqDTO;
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
public interface PermissionFacade {

    @PostMapping("/operations")
    ResponseWrapper<Long> createOperation(
            @RequestBody @Validated CreateOperationReqDTO createOperationReqDTO,
            @RequestHeader("tenantId") Long tenantId
    );

    @PostMapping("/resources")
    ResponseWrapper<Long> createResource(
            @RequestBody @Validated CreateResourceReqDTO createResourceReqDTO,
            @RequestHeader("tenantId") Long tenantId
    );

    @PostMapping("/permissions")
    ResponseWrapper<Long> createPermission(
            @RequestBody @Validated CreatePermissionReqDTO createPermissionReqDTO,
            @RequestHeader("tenantId") Long tenantId
    );

    @PostMapping("/tenants")
    ResponseWrapper<Long> createTenant(
            @RequestBody @Validated CreateTenantReqDTO createTenantReqDTO
    );
}
