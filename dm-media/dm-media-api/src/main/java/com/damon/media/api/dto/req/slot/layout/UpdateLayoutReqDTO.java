package com.damon.media.api.dto.req.slot.layout;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/***
 * 更新信息流样式请求
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "修改信息流样式")
public final class UpdateLayoutReqDTO implements Serializable {
    private static final long serialVersionUID = 3021369610157096L;

    @ApiModelProperty(value = "信息流样式ID", required = true)
    @NotNull(message = "信息流样式ID不能为空")
    @Min(value = 100000000000000L, message = "信息流样式ID不合法")
    private Long layoutId;

    @ApiModelProperty(value = "宽度", required = true)
    @NotNull(message = "宽度不能为空")
    @Min(value = 0, message = "宽度必须大于0")
    private Integer width;

    @ApiModelProperty(value = "高度", required = true)
    @NotNull(message = "高度不能为空")
    @Min(value = 0, message = "高度必须大于0")
    private Integer height;

    @ApiModelProperty(value = "信息流样式示意图", required = true)
    @NotNull(message = "示意图不能为空")
    private String snapshot;
}
