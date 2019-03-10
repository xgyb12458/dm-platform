package com.damon.media.api.dto.resp.slot;

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
@ApiModel(value = "查询广告位的响应信息")
public class SlotInfoRespDTO implements Serializable {
    private static final long serialVersionUID = 96993157176L;

    @ApiModelProperty(value = "资源位ID")
    private Long slotId;

    @ApiModelProperty(value = "应用ID")
    private List<Long> appIds;

    @ApiModelProperty(value = "资源位名称")
    private String name;

    @ApiModelProperty(value = "资源位别名")
    private String alias;

    @ApiModelProperty(value = "资源位类型")
    private String type;

    @ApiModelProperty(value = "支持系统类型")
    private String os;

    @ApiModelProperty(value = "资源位规格ID")
    private Long specId;

    @ApiModelProperty(value = "资源位所属频道")
    private String channel;

    @ApiModelProperty(value = "资源位示意图")
    private String snapshot;

    @ApiModelProperty(value = "屏蔽行业")
    private String blockIndustry;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "所属用户Id")
    private Long userId;

    @ApiModelProperty(value = "启停状态")
    private String state;

    @ApiModelProperty(value = "审核状态")
    private String status;

    @ApiModelProperty(value = "是否可编辑")
    private Boolean editable;
}
