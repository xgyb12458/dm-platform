package com.damon.oauth.manager.dto.req.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 查询权限域
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "创建权限所需参数")
public class QueryPermissionReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源ID", required = true)
    @NotNull(message = "请输入资源ID")
    private Long resourceId;


    @ApiModelProperty(value = "操作ID", required = true)
    @NotNull(message = "请输入操作ID")
    private Long operationId;

}

