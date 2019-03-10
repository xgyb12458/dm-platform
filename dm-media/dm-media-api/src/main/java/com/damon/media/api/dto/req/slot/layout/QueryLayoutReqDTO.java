package com.damon.media.api.dto.req.slot.layout;

import com.damon.media.api.dto.req.PageableReqDTO;
import com.damon.media.shared.enums.LayoutType;
import com.damon.shared.enums.SwitchState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "查询信息流布局样式请求")
public final class QueryLayoutReqDTO extends PageableReqDTO implements Serializable {
    private static final long serialVersionUID = 6069699057176L;

    @ApiModelProperty(value = "样式宽度")
    @Min(value = 0, message = "宽度必须大于0")
    private Integer width;

    @ApiModelProperty(value = "样式高度")
    @Min(value = 0, message = "高度必须大于0")
    private Integer height;

    @ApiModelProperty(value = "启停状态")
    private SwitchState state;

    @ApiModelProperty(value = "样式类型")
    private LayoutType layoutType;
}
