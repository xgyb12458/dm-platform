package com.damon.oauth.core.web.manager.facade.impl;

import com.damon.oauth.domain.permission.command.*;
import com.damon.oauth.domain.resource.command.UpdateResourceCommand;
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
import com.damon.oauth.manager.web.facade.PermissionFacade;
import com.damon.shared.common.Pagination;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限管理后端接口
 * @author Damon S.
 */
@Log4j2
@RestController
@Api(tags = "权限管理接口")
@RequiredArgsConstructor
public class PermissionFacadeImpl implements PermissionFacade {


    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;


    @ArgsValid
    @Override
    @ApiOperation(value = "创建操作域", notes = "创建操作域")
    public ResponseWrapper<Long> createOperation(CreateOperationReqDTO createOperationReqDTO) {
        Long currentUserId = 0L;
        CreateOperationCommand command = CreateOperationCommand.builder()
                .code(createOperationReqDTO.getCode())
                .name(createOperationReqDTO.getName())
                .createdBy(currentUserId)
                .build();
        Long createdOperationId = commandGateway.sendAndWait(command);

        if (0 < createdOperationId) {
            return new ResponseWrapper<>(createdOperationId);
        } else {
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "更新操作域", notes = "更新操作域")
    public ResponseWrapper<Boolean> updateOperation(UpdateOperationReqDTO updateOperationReqDTO) {
        Long currentUserId = 0L;
        UpdateOperationCommand command = UpdateOperationCommand.builder()
                .name(updateOperationReqDTO.getName())
                .operationId(updateOperationReqDTO.getOperationId())
                .updatedBy(currentUserId)
                .build();

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "查询操作域", notes = "查询操作域")
    public ResponseWrapper<Pagination<OperationInfoRespDTO>> listOperations(
            QueryOperationReqDTO queryOperationReqDTO) {
        QueryOperationCommand command = QueryOperationCommand.builder()
                .build();

        queryGateway.query(command, String.class);

        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "获取指定操作域", notes = "获取指定操作域")
    public ResponseWrapper<OperationInfoRespDTO> findOperation(Long operationId) {

        return new ResponseWrapper<>();
    }


    @Override
    @ApiOperation(value = "删除操作域", notes = "删除操作域")
    public ResponseWrapper<Boolean> removeOperation(Long operationId) {
        Long currentUserId = 0L;
        RemoveOperationCommand command = new RemoveOperationCommand(operationId, currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "创建资源域", notes = "创建资源域")
    public ResponseWrapper<Long> createResource(CreateResourceReqDTO createResourceReqDTO) {
        Long currentUserId = 0L;
        CreateResourceCommand command = CreateResourceCommand.builder()
                .code(createResourceReqDTO.getCode())
                .name(createResourceReqDTO.getName())
                .createdBy(currentUserId)
                .build();
        Long createdResourceId = commandGateway.sendAndWait(command);

        if (0 < createdResourceId) {
            return new ResponseWrapper<>(createdResourceId);
        } else {
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "更新资源域", notes = "更新资源域")
    public ResponseWrapper<Boolean> updateResource(UpdateResourceReqDTO updateResourceReqDTO) {
        Long currentUserId = 0L;
        UpdateResourceCommand command = UpdateResourceCommand.builder()
                .code(updateResourceReqDTO.getCode())
                .name(updateResourceReqDTO.getName())
                .resourceId(new ResourceId(updateResourceReqDTO.getResourceId()))
                .parentId(updateResourceReqDTO.getParentId())
                .updatedBy(currentUserId)
                .build();

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "查询资源域", notes = "查询资源域")
    public ResponseWrapper<Pagination<ResourceInfoRespDTO>> listResources(
            QueryResourceReqDTO queryResourceReqDTO) {

        return new ResponseWrapper<>();
    }


    @Override
    @ApiOperation(value = "删除资源域", notes = "删除资源域")
    public ResponseWrapper<Boolean> removeResource(Long resourceId) {
        Long currentUserId = 0L;
        RemoveResourceCommand command = new RemoveResourceCommand(resourceId, currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "获取指定资源域", notes = "获取指定资源域")
    public ResponseWrapper<ResourceInfoRespDTO> findResource(Long resourceId) {

        return new ResponseWrapper<>();
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "创建权限域", notes = "创建权限域")
    public ResponseWrapper<Long> createPermission(CreatePermissionReqDTO createPermissionReqDTO) {
        Long currentUserId = 0L;
        CreatePermissionCommand command = CreatePermissionCommand.builder()
                .operationId(createPermissionReqDTO.getOperationId())
                .resourceId(createPermissionReqDTO.getResourceId())
                .createdBy(currentUserId)
                .build();
        Long createdPermissionId = commandGateway.sendAndWait(command);

        if (0 < createdPermissionId) {
            return new ResponseWrapper<>(createdPermissionId);
        } else {
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "查询权限域", notes = "查询权限域")
    public ResponseWrapper<Pagination<PermissionInfoRespDTO>> listPermissions(
            QueryPermissionReqDTO queryPermissionReqDTO) {
        return new ResponseWrapper<>();
    }


    @Override
    @ApiOperation(value = "获取指定权限域", notes = "获取指定权限域")
    public ResponseWrapper<PermissionInfoRespDTO> findPermission(Long permissionId) {
        return new ResponseWrapper<>();
    }


    @Override
    @ApiOperation(value = "删除权限域", notes = "删除权限域")
    public ResponseWrapper<Boolean> removePermission(Long permissionId) {
        Long currentUserId = 0L;
        RemovePermissionCommand command = new RemovePermissionCommand(permissionId, currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }
}
