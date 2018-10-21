package com.damon.order.api.dto.resp.operation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Damon S.
 */
@Data
@ToString
public class OperationInfoRespDTO {


    @ApiModelProperty(name = "operationId", value = "操作ID")
    private String operationId;


    @ApiModelProperty(name = "code", value = "操作编码")
    private String code;


    @ApiModelProperty(name = "name", value = "操作名称")
    private String name;
}
