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

    @ApiModelProperty(value = "规格ID")
    private Long specId;

    @ApiModelProperty(value = "宽度")
    private Integer width;

    @ApiModelProperty(value = "高度")
    private Integer height;

    @ApiModelProperty(value = "图片类型")
    private String imageType;

    @ApiModelProperty(value = "图片大小(单位:K)")
    private Integer imageSize;

    @ApiModelProperty(value = "资源位类型")
    private String slotType;

    @ApiModelProperty(value = "信息流布局样式")
    private List<Long> layoutIds;

    @ApiModelProperty(value = "资源位规格示意图")
    private String snapshot;

    @ApiModelProperty(value = "启停状态")
    private String state;

    @ApiModelProperty(value = "轮播帧数")
    private Integer frameCount;

    @ApiModelProperty(value = "皮肤")
    private String lookAndFeel;

    @ApiModelProperty(value = "是否可进行编辑")
    private Boolean editable;
}
