package com.damon.product.api.dto.resp.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 操作日志信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月12日 08:52
 */
@Data
@ApiModel(value = "操作日志信息")
public class OperateLogRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long        logId;

    @ApiModelProperty(value = "操作对象标识")
    private Long        objectId;

    @ApiModelProperty(value = "操作目标类型")
    private String      target;

    @ApiModelProperty(value = "操作类型")
    private String      type;

    @ApiModelProperty(value = "操作内容")
    private String      content;

    @ApiModelProperty(value = "操作人")
    private Long        operatedBy;

    @ApiModelProperty(value = "操作时间")
    private Long        operatedAt;
}
