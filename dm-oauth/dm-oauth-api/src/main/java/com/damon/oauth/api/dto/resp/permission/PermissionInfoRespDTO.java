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

    @ApiModelProperty(value = "权限ID")
    private String permissionId;

    @ApiModelProperty(value = "资源ID", required = true)
    private Long resourceId;

    @ApiModelProperty(value = "操作ID", required = true)
    private Long operationId;
}
