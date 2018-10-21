package com.damon.order.core.impl;

import com.damon.order.api.facade.AuthorityFacade;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Damon S.
 */
@RestController
@Api(tags = "权限管理基本接口")
public class AuthorityFacadeImpl implements AuthorityFacade {

//    @ArgsValid
//    @Override
//    @ApiOperation(value = "用户登录", notes = "用户登录（用户名/密码/验证码）。")
//    public ResponseWrapper<UserInfoRespDTO> login(CreateResourceReqDTO loginDTO) {
//        return null;
//    }
//
//    @Override
//    public ResponseWrapper<Boolean> logout(Long userId) {
//        return null;
//    }

//    @Override
//    public ResponseWrapper<Boolean> createOperation(
//            CreateOperationReqDTO createOperationReqDTO, Long tenantId) {
//        return null;
//    }
//
//    @Override
//    public ResponseWrapper<Boolean> createPermission(
//            CreatePermissionReqDTO createPermissionReqDTO, Long tenantId) {
//        CreatePermissionCommand command = CreatePermissionCommand.builder()
//                .resourceId(createPermissionReqDTO.getResourceId())
//                .operationId(createPermissionReqDTO.getOperationId())
//                .createdBy(0L)
//                .tenantId(new TenantId(tenantId))
//                .build();
//        return null;
//    }
//
//    @Override
//    public ResponseWrapper<Boolean> createResource(
//            CreateResourceReqDTO createResourceReqDTO, Long tenantId) {
//        return null;
//    }
//
//    @Override
//    public ResponseWrapper<Boolean> createRole(
//            CreateRoleReqDTO createRoleReqDTO, Long tenantId) {
//        return null;
//    }
//
//    @Override
//    public ResponseWrapper<Boolean> createTenant(CreateTenantReqDTO createTenantReqDTO) {
//        return null;
//    }
//
//    @Override
//    public ResponseWrapper<Boolean> createUser(
//            CreateUserReqDTO createUserReqDTO, Long tenantId) {
//        return null;
//    }
//
//    @Override
//    public ResponseWrapper<Boolean> obtainCaptcha(ObtainCaptchaReqDTO obtainCaptchaReqDTO) {
//        return null;
//    }
//
//    @ArgsValid
//    @Override
//    @ApiOperation(value = "用户列表", notes = "分页查询注册用户，列表显示。")
//    public ResponseWrapper<Pagination<UserInfoRespDTO>> listUsers(QueryUserReqDTO userQueryReqDTO) {
//        return null;
//    }
}
