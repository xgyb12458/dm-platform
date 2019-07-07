package com.damon.oauth.manager.dto.resp.operation;

import com.damon.shared.enums.SwitchState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 操作信息
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "操作信息")
public class OperationInfoRespDTO {

    @ApiModelProperty(value = "操作ID")
    private String operationId;

    @ApiModelProperty(value = "操作编码")
    private String code;

    @ApiModelProperty(value = "操作名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private SwitchState state;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "创建时间")
    private Long createdAt;

    @ApiModelProperty(value = "创建人")
    private String createdBy;
}
