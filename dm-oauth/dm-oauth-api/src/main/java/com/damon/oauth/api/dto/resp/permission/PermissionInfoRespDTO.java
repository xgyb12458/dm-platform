package com.damon.oauth.api.dto.resp.permission;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Damon
 */
@Data
@ToString
public class PermissionInfoRespDTO {

    @ApiModelProperty(name = "permissionId", value = "权限ID")
    private String permissionId;

    @ApiModelProperty(name = "resourceId", value = "资源ID", required = true)
    private Long resourceId;

    @ApiModelProperty(name = "operationId", value = "操作ID", required = true)
    private Long operationId;
}
