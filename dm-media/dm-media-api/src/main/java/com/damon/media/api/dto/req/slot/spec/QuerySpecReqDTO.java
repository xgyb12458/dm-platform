package com.damon.media.api.dto.req.slot.spec;

import com.damon.media.api.dto.req.PageableReqDTO;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 查询资源位规格所需参数类
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "查询资源位规格信息")
public final class QuerySpecReqDTO extends PageableReqDTO implements Serializable {
    private static final long serialVersionUID = 2608969900176L;

    @ApiModelProperty(value = "宽度")
    @Min(value = 0, message = "宽度必须大于0")
    private Integer width;

    @ApiModelProperty(value = "高度")
    @Min(value = 0, message = "高度必须大于0")
    private Integer height;

    @ApiModelProperty(value = "图片类型(PNG,JPG,GIF)")
    private String imageType;

    @ApiModelProperty(value = "资源位类型(SPLASH|BULLET|BANNER|FEEDS)")
    private SlotType slotType;

    @ApiModelProperty(value = "启停状态")
    private SwitchState state;


}
