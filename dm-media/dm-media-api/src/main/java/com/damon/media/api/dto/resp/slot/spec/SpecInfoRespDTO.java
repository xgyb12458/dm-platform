package com.damon.media.api.dto.resp.slot.spec;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Damon S.
 */
@ToString
@Data
@NoArgsConstructor
@ApiModel(value = "资源位规格详情信息")
public class SpecInfoRespDTO implements Serializable {
    private static final long serialVersionUID = 7520161591015L;

    @ApiModelProperty(name = "specId", value = "规格ID")
    private Long specId;

    @ApiModelProperty(name = "width", value = "宽度")
    private Integer width;

    @ApiModelProperty(name = "height", value = "高度")
    private Integer height;

    @ApiModelProperty(name = "imageType", value = "图片类型")
    private String imageType;

    @ApiModelProperty(name = "imageSize", value = "图片大小(单位:K)")
    private Integer imageSize;

    @ApiModelProperty(name = "slotType", value = "资源位类型")
    private String slotType;

    @ApiModelProperty(name = "layoutIds", value = "信息流布局样式")
    private List<Long> layoutIds;

    @ApiModelProperty(name = "snapshot", value = "资源位规格示意图")
    private String snapshot;

    @ApiModelProperty(name = "state", value = "启停状态")
    private String state;

    @ApiModelProperty(name = "frameCount", value = "轮播帧数")
    private Integer frameCount;

    @ApiModelProperty(name = "lookAndFeel", value = "皮肤")
    private String lookAndFeel;

    @ApiModelProperty(name = "editable", value = "是否可进行编辑")
    private Boolean editable;
}
