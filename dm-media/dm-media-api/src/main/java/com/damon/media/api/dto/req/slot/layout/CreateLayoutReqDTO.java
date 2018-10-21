package com.damon.media.api.dto.req.slot.layout;

import com.damon.media.shared.enums.LayoutType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 创新信息流样式所需参数类
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "创建信息流样式请求")
public final class CreateLayoutReqDTO implements Serializable {
    private static final long serialVersionUID = 20696993157176L;

    @ApiModelProperty(name = "width", value = "宽度", required = true)
    @NotNull(message = "宽度不能为空")
    @Min(value = 0, message = "宽度必须大于0")
    private Integer width;

    @ApiModelProperty(name = "height", value = "高度", required = true)
    @NotNull(message = "高度不能为空")
    @Min(value = 0, message = "高度必须大于0")
    private Integer height;

    @ApiModelProperty(name = "layoutType", value = "样式类型", required = true)
    @NotNull(message = "样式类型不能为空")
    private LayoutType layoutType;

    @ApiModelProperty(name = "snapshot", value = "信息流样式示意图", required = true)
    @NotNull(message = "示意图不能为空")
    private String snapshot;
}
