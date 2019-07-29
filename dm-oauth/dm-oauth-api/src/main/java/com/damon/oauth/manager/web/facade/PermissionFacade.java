package com.damon.oauth.manager.web.facade;

import com.damon.oauth.manager.dto.req.operation.CreateOperationReqDTO;
import com.damon.oauth.manager.dto.req.operation.QueryOperationReqDTO;
import com.damon.oauth.manager.dto.req.operation.UpdateOperationReqDTO;
import com.damon.oauth.manager.dto.req.permission.CreatePermissionReqDTO;
import com.damon.oauth.manager.dto.req.permission.QueryPermissionReqDTO;
import com.damon.oauth.manager.dto.req.resource.CreateResourceReqDTO;
import com.damon.oauth.manager.dto.req.resource.QueryResourceReqDTO;
import com.damon.oauth.manager.dto.req.resource.UpdateResourceReqDTO;
import com.damon.oauth.manager.dto.resp.operation.OperationInfoRespDTO;
import com.damon.oauth.manager.dto.resp.permission.PermissionInfoRespDTO;
import com.damon.oauth.manager.dto.resp.resource.ResourceInfoRespDTO;
import com.damon.oauth.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
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

    @GetMapping("/operations")
    ResponseWrapper<Pagination<OperationInfoRespDTO>> listOperations(
            @RequestBody @Validated QueryOperationReqDTO queryOperationReqDTO
    );

    @GetMapping("/operations/{operationId}")
    ResponseWrapper<OperationInfoRespDTO> findOperation(
            @PathVariable("operationId") Long operationId
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

    @GetMapping("/resources")
    ResponseWrapper<Pagination<ResourceInfoRespDTO>> listResources(
            @RequestBody @Validated QueryResourceReqDTO queryResourceReqDTO
    );

    @DeleteMapping("/resources/{resourceId}")
    ResponseWrapper<Boolean> removeResource(
            @PathVariable("resourceId") Long resourceId
    );

    @GetMapping("/resources/{resourceId}")
    ResponseWrapper<ResourceInfoRespDTO> findResource(
            @PathVariable("resourceId") Long resourceId
    );

    @PostMapping("/permissions")
    ResponseWrapper<Long> createPermission(
            @RequestBody @Validated CreatePermissionReqDTO createPermissionReqDTO
    );

    @GetMapping("/permissions")
    ResponseWrapper<Pagination<PermissionInfoRespDTO>> listPermissions(
            @RequestBody @Validated QueryPermissionReqDTO queryPermissionReqDTO
    );

    @GetMapping("/permissions/{permissionId}")
    ResponseWrapper<PermissionInfoRespDTO> findPermission(
            @PathVariable("permissionId") Long permissionId
    );

    @DeleteMapping("/permissions/{permissionId}")
    ResponseWrapper<Boolean> removePermission(
            @PathVariable("permissionId") Long permissionId
    );
}
