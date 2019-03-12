package com.damon.product.api.dto.req.log;

import com.damon.shared.dto.PageableReqDTO;
import com.damon.shared.enums.OperateTarget;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 查询操作日志
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月12日 08:51
 */
@Data
@ApiModel(value = "查询操作日志")
public class QueryOperateLogReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "操作目标对象类型")
    private OperateTarget target;

    @ApiModelProperty(value = "操作对象Id")
    private Long      objectId;

    @ApiModelProperty(value = "操作人")
    private Long      operatedBy;

    @ApiModelProperty(value = "操作时间-从（毫秒数）")
    @Min(value = 1551161592000L, message = "开始时间毫秒数不合法")
    private Long      operatedFrom;

    @ApiModelProperty(value = "操作时间-至（毫秒数）")
    @Min(value = 1551161592000L, message = "开始时间毫秒数不合法")
    private Long      operatedTo;
}
