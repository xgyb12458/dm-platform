package com.damon.oauth.manager.web.facade;

import com.damon.oauth.manager.dto.req.operation.CreateOperationReqDTO;
import com.damon.oauth.manager.dto.req.operation.UpdateOperationReqDTO;
import com.damon.oauth.manager.dto.req.permission.CreatePermissionReqDTO;
import com.damon.oauth.manager.dto.req.permission.UpdatePermissionReqDTO;
import com.damon.oauth.manager.dto.req.resource.CreateResourceReqDTO;
import com.damon.oauth.manager.dto.req.resource.UpdateResourceReqDTO;
import com.damon.oauth.manager.dto.resp.permission.PermissionInfoRespDTO;
import com.damon.oauth.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户权限管理
 * @author Damon S.
 */
@RequestMapping(value = "/oauth/manager" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface PermissionFacade {

    @PostMapping("/operations")
    ResponseWrapper<Long> createOperation(
            @RequestBody @Validated CreateOperationReqDTO createOperationReqDTO
    );

    @PutMapping("/operations")
    ResponseWrapper<Boolean> updateOperation(
            @RequestBody @Validated UpdateOperationReqDTO updateTenantReqDTO
    );

    @DeleteMapping("/operations/{operationId}")
    ResponseWrapper<Boolean> removeOperation(
            @PathVariable("operationId") Long operationId
    );

    @PostMapping("/resources")
    ResponseWrapper<Long> createResource(
            @RequestBody @Validated CreateResourceReqDTO createResourceReqDTO
    );

    @PutMapping("/resources")
    ResponseWrapper<Boolean> updateResource(
            @RequestBody @Validated UpdateResourceReqDTO updateResourceReqDTO
    );

    @DeleteMapping("/resources/{resourceId}")
    ResponseWrapper<Boolean> removeResource(
            @PathVariable("resourceId") Long resourceId
    );

    @PostMapping("/permissions")
    ResponseWrapper<Long> createPermission(
            @RequestBody @Validated CreatePermissionReqDTO createPermissionReqDTO
    );

    @PutMapping("/permissions")
    ResponseWrapper<Boolean> updatePermission(
            @RequestBody @Validated UpdatePermissionReqDTO updatePermissionReqDTO
    );

    @GetMapping("/permissions/{permissionId}")
    ResponseWrapper<PermissionInfoRespDTO> getPermission(
            @PathVariable("permissionId") Long permissionId
    );

    @DeleteMapping("/permissions/{permissionId}")
    ResponseWrapper<Boolean> removePermission(
            @PathVariable("permissionId") Long permissionId
    );
}
