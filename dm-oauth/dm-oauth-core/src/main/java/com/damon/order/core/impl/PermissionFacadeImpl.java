//package com.damon.oauth.core.web.facade.impl;
//
//import com.damon.oauth.api.dto.req.operation.CreateOperationReqDTO;
//import com.damon.oauth.api.dto.req.permission.CreatePermissionReqDTO;
//import com.damon.oauth.api.dto.req.resource.CreateResourceReqDTO;
//import com.damon.oauth.api.dto.req.tenant.CreateTenantReqDTO;
//import com.damon.oauth.api.web.facade.PermissionFacade;
//import com.damon.oauth.domain.permission.command.CreateOperationCommand;
//import com.damon.oauth.domain.permission.command.CreatePermissionCommand;
//import com.damon.oauth.domain.permission.command.CreateResourceCommand;
//import com.damon.oauth.domain.permission.command.CreateTenantCommand;
//import com.damon.shared.enums.ResponseCodeEnum;
//import com.damon.shared.tenant.TenantId;
//import com.damon.shared.validation.ArgsValid;
//import com.damon.shared.wrapper.ResponseWrapper;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author Damon S.
// */
//@RestController
//@Api(tags = "权限管理接口")
//public class PermissionFacadeImpl implements PermissionFacade {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionFacadeImpl.class);
//
//    private final CommandGateway commandGateway;
//
//
//    public PermissionFacadeImpl(CommandGateway commandGateway) {
//        this.commandGateway = commandGateway;
//    }
//
//    @ArgsValid
//    @Override
//    @ApiOperation(value = "创建操作", notes = "创建操作")
//    public ResponseWrapper<Long> createOperation(CreateOperationReqDTO createOperationReqDTO, Long tenantId) {
//        CreateOperationCommand command = CreateOperationCommand.builder()
//                .code(createOperationReqDTO.getCode())
//                .name(createOperationReqDTO.getName())
//                .tenantId(new TenantId(tenantId))
//                .createdBy(0L)
//                .build();
//        Long createdOperationId = commandGateway.sendAndWait(command);
//
//        if (0 < createdOperationId) {
//            return new ResponseWrapper<>(createdOperationId);
//        } else {
//            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
//        }
//    }
//
//    @ArgsValid
//    @Override
//    @ApiOperation(value = "创建资源", notes = "创建资源")
//    public ResponseWrapper<Long> createResource(CreateResourceReqDTO createResourceReqDTO, Long tenantId) {
//        CreateResourceCommand command = CreateResourceCommand.builder()
//                .code(createResourceReqDTO.getCode())
//                .name(createResourceReqDTO.getName())
//                .tenantId(new TenantId(tenantId))
//                .createdBy(0L)
//                .build();
//        Long createdResourceId = commandGateway.sendAndWait(command);
//
//        if (0 < createdResourceId) {
//            return new ResponseWrapper<>(createdResourceId);
//        } else {
//            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
//        }
//    }
//
//    @ArgsValid
//    @Override
//    @ApiOperation(value = "创建权限", notes = "创建权限")
//    public ResponseWrapper<Long> createPermission(CreatePermissionReqDTO createPermissionReqDTO, Long tenantId) {
//        CreatePermissionCommand command = CreatePermissionCommand.builder()
//                .operationId(createPermissionReqDTO.getOperationId())
//                .resourceId(createPermissionReqDTO.getResourceId())
//                .tenantId(new TenantId(tenantId))
//                .createdBy(0L)
//                .build();
//        Long createdPermissionId = commandGateway.sendAndWait(command);
//
//        if (0 < createdPermissionId) {
//            return new ResponseWrapper<>(createdPermissionId);
//        } else {
//            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
//        }
//    }
//
//    @ArgsValid
//    @Override
//    @ApiOperation(value = "创建租户", notes = "创建租户")
//    public ResponseWrapper<Long> createTenant(CreateTenantReqDTO createTenantReqDTO) {
//        CreateTenantCommand command = CreateTenantCommand.builder()
//                .code(createTenantReqDTO.getCode())
//                .name(createTenantReqDTO.getName())
//                .createdBy(0L)
//                .build();
//        Long createdTenantId = commandGateway.sendAndWait(command);
//
//        if (0 < createdTenantId) {
//            return new ResponseWrapper<>(createdTenantId);
//        } else {
//            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
//        }
//    }
//}
