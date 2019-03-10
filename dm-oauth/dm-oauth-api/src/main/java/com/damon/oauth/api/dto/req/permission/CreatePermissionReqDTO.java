package com.damon.oauth.api.dto.req.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 用户登录请求
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "创建权限所需参数")
public class CreatePermissionReqDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源ID", required = true)
    @NotNull(message = "请输入资源ID")
    private Long resourceId;


    @ApiModelProperty(value = "操作ID", required = true)
    @NotNull(message = "请输入操作ID")
    private Long operationId;

}

