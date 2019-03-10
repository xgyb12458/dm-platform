package com.damon.media.api.dto.resp.slot.layout;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 信息流样式详情信息
 * @author Damon S.
 */
@ToString
@Data
@NoArgsConstructor
@ApiModel(value = "信息流样式详情信息")
public class LayoutInfoRespDTO implements Serializable {
    private static final long serialVersionUID = 25201614910176L;

    @ApiModelProperty(value = "信息流样式ID")
    private Long layoutId;

    @ApiModelProperty(value = "宽度")
    private Integer width;

    @ApiModelProperty(value = "高度")
    private Integer height;

    @ApiModelProperty(value = "样式类型")
    private String layoutType;

    @ApiModelProperty(value = "图片数量")
    private Integer imageCount;

    @ApiModelProperty(value = "信息流样式示意图")
    private String snapshot;

    @ApiModelProperty(value = "启停状态")
    private String state;

    @ApiModelProperty(value = "是否可进行编辑")
    private Boolean editable;
}
