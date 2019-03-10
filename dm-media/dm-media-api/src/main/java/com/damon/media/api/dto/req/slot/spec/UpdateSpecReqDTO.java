package com.damon.media.api.dto.req.slot.spec;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


/***
 * 更新资源位请求
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "修改资源位规格")
public final class UpdateSpecReqDTO implements Serializable {
    private static final long serialVersionUID = 3021369610157096L;

    @ApiModelProperty(value = "资源位规格ID", required = true)
    @NotNull(message = "资源位规格ID不能为空")
    @Min(value = 100000000000000L, message = "资源位规格ID不合法")
    private Long specId;

    @ApiModelProperty(value = "宽度")
    @NotNull(message = "宽度不能为空")
    @Min(value = 0, message = "宽度必须大于0")
    private Integer width;

    @ApiModelProperty(value = "高度")
    @NotNull(message = "高度不能为空")
    @Min(value = 0, message = "高度必须大于0")
    private Integer height;

    @ApiModelProperty(value = "图片类型(PNG,JPG,GIF)")
    @NotNull(message = "图片类型不能为空")
    @Pattern(regexp = "(PNG|JPG|GIF)", message = "图片类型取值为(PNG,JPG,GIF)")
    private String imageType;

    @ApiModelProperty(value = "图片大小(单位:K)")
    @NotNull(message = "图片大小不能为空")
    @Min(value = 0, message = "图片大小必须大于0")
    private Integer imageSize;

    @ApiModelProperty(value = "信息流布局样式")
    private List<Long> layoutIds;

    @ApiModelProperty(value = "资源位规格示意图")
    private String snapshot;

    @ApiModelProperty(value = "轮播帧数")
    @Min(value = 1, message = "帧数必须大于1")
    private Integer frameCount;

    @ApiModelProperty(value = "皮肤")
    private String lookAndFeel;
}
