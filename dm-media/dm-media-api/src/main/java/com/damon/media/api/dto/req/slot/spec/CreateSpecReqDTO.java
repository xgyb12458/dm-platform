package com.damon.media.api.dto.req.slot.spec;

import com.damon.media.shared.enums.SlotType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 创建资源位规格所需参数类
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "创建资源位规格")
public final class CreateSpecReqDTO implements Serializable {
    private static final long serialVersionUID = 1210696993157176L;

    @ApiModelProperty(value = "宽度", required = true)
    @NotNull(message = "宽度不能为空")
    @Min(value = 0, message = "宽度必须大于0")
    private Integer width;

    @ApiModelProperty(value = "高度", required = true)
    @NotNull(message = "高度不能为空")
    @Min(value = 0, message = "高度必须大于0")
    private Integer height;

    @ApiModelProperty(value = "资源位类型(SPLASH|BULLET|BANNER|FEEDS)", required = true)
    @NotNull(message = "资源位类型不能为空")
    private SlotType slotType;

    @ApiModelProperty(value = "图片类型(PNG,JPG,GIF)", required = true)
    @NotNull(message = "图片类型不能为空")
//    @Pattern(regexp = "^((PNG)|(JPG)|(GIF))+", message = "图片类型取值应为(PNG,JPG,GIF)之一种或多种")
    private String imageType;

    @ApiModelProperty(value = "图片大小(单位:K)", required = true)
    @NotNull(message = "图片大小不能为空")
    @Min(value = 0, message = "图片大小必须大于0")
    private Integer imageSize;

    @ApiModelProperty(value = "资源位规格示意图", required = true)
    @NotNull(message = "示意图不能为空")
    private String snapshot;

    @ApiModelProperty(value = "信息流布局样式")
    private List<Long> layoutIds;

    @ApiModelProperty(value = "轮播帧数")
    @Min(value = 1, message = "帧数必须大于1")
    private Integer frameCount;

    @ApiModelProperty(value = "皮肤")
    private String lookAndFeel;
}
