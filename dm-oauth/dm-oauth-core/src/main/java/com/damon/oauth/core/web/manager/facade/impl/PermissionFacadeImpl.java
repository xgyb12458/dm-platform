package com.damon.oauth.core.web.manager.facade.impl;

import com.damon.oauth.domain.permission.command.*;
import com.damon.oauth.domain.resource.command.UpdateResourceCommand;
import com.damon.oauth.manager.dto.req.operation.CreateOperationReqDTO;
import com.damon.oauth.manager.dto.req.operation.UpdateOperationReqDTO;
import com.damon.oauth.manager.dto.req.permission.CreatePermissionReqDTO;
import com.damon.oauth.manager.dto.req.permission.UpdatePermissionReqDTO;
import com.damon.oauth.manager.dto.req.resource.CreateResourceReqDTO;
import com.damon.oauth.manager.dto.req.resource.UpdateResourceReqDTO;
import com.damon.oauth.manager.dto.req.tenant.CreateTenantReqDTO;
import com.damon.oauth.manager.web.facade.PermissionFacade;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.tenant.TenantId;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Damon S.
 */
@Log4j2
@RestController
@Api(tags = "权限管理接口")
public class PermissionFacadeImpl implements PermissionFacade {


    private final CommandGateway commandGateway;


    public PermissionFacadeImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "创建操作域", notes = "创建操作域")
    public ResponseWrapper<Long> createOperation(CreateOperationReqDTO createOperationReqDTO) {
        CreateOperationCommand command = CreateOperationCommand.builder()
                .code(createOperationReqDTO.getCode())
                .name(createOperationReqDTO.getName())
//                .tenantId(new TenantId(tenantId))
                .createdBy(0L)
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
    public ResponseWrapper<Boolean> updateOperation(UpdateOperationReqDTO updateTenantReqDTO) {
        UpdateOperationCommand command = UpdateOperationCommand.builder().builder();
        return null;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "删除操作域", notes = "删除操作域")
    public ResponseWrapper<Boolean> removeOperation(Long operationId) {
        RemoveOperationCommand command = RemoveOperationCommand.builder().build();
        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "创建资源域", notes = "创建资源域")
    public ResponseWrapper<Long> createResource(CreateResourceReqDTO createResourceReqDTO) {
        CreateResourceCommand command = CreateResourceCommand.builder()
                .code(createResourceReqDTO.getCode())
                .name(createResourceReqDTO.getName())
//                .tenantId(new TenantId(tenantId))
                .createdBy(0L)
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
        UpdateResourceCommand command = UpdateResourceCommand.builder().build();
        return null;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "删除资源域", notes = "删除资源域")
    public ResponseWrapper<Boolean> removeResource(Long resourceId) {
        RemoveResourceCommand command = RemoveResourceCommand.builder().build();
        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "创建权限域", notes = "创建权限域")
    public ResponseWrapper<Long> createPermission(CreatePermissionReqDTO createPermissionReqDTO) {
        CreatePermissionCommand command = CreatePermissionCommand.builder()
                .operationId(createPermissionReqDTO.getOperationId())
                .resourceId(createPermissionReqDTO.getResourceId())
//                .tenantId(new TenantId(tenantId))
                .createdBy(0L)
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
    @ApiOperation(value = "更新权限域", notes = "更新权限域")
    public ResponseWrapper<Boolean> updatePermission(UpdatePermissionReqDTO updatePermissionReqDTO) {
        UpdatePermissionCommand command = UpdatePermissionCommand.builder().build();
        return null;
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "删除权限域", notes = "删除权限域")
    public ResponseWrapper<Boolean> removePermission(Long permissionId) {
        RemovePermissionCommand command = RemovePermissionCommand.builder().build();
        return null;
    }
}
