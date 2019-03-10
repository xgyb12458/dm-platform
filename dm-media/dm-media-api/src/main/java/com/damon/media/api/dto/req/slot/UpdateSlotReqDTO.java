package com.damon.media.api.dto.req.slot;

import com.damon.shared.enums.OSCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/***
 * 更新资源位请求
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "媒体应用属性编辑参数")
public final class UpdateSlotReqDTO implements Serializable {
    private static final long serialVersionUID = 3021369610157096L;

    @ApiModelProperty(value = "资源位标识ID", required = true)
    @NotNull(message = "资源位标识不能为空")
    @Min(value = 100000000000000L, message = "资源位标识不合法")
    private Long slotId;

    @ApiModelProperty(value = "资源位规格标识ID")
    @NotNull(message = "资源位规格标识不能为空")
    @Min(value = 100000000000000L, message = "资源位规格标识不合法")
    private Long specId;

    @ApiModelProperty(value = "支持系统类型(IOS|ANDROID|IOSROID|NA)")
    private OSCategory os;

    @ApiModelProperty(value = "屏蔽行业")
    private String blockIndustry;

    @ApiModelProperty(value = "资源位所属频道")
    private String channel;

    @ApiModelProperty(value = "资源位示意图")
    private String snapshot;

    @ApiModelProperty(value = "描述")
    private String description;
}
