package com.damon.media.api.dto.req.slot;

import com.damon.shared.enums.OSCategory;
import com.damon.media.shared.enums.SlotType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 创建资源位所需参数类
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "物料数据查询请求")
public final class CreateSlotReqDTO implements Serializable {
    private static final long serialVersionUID = 1210696993157176L;

    @ApiModelProperty(value = "应用ID", required = true)
    @NotNull(message = "应用ID不能为空")
    private List<Long> appIds;

    @ApiModelProperty(value = "资源位名称", required = true)
    @NotNull(message = "资源位名称不能为空")
    @Length(max = 30, message = "资源位名称不得超过30个字符")
    private String name;

    @ApiModelProperty(value = "资源位规格ID", required = true)
    @NotNull(message = "规格ID不能为空")
    @Min(value = 100000000000000L, message = "资源位标识不合法")
    private Long specId;

    @ApiModelProperty(value = "资源位类型(SPLASH|BULLET|BANNER|FEEDS)", required = true)
    @NotNull(message = "资源位类型不能为空")
    private SlotType type;

    @ApiModelProperty(value = "支持系统类型(IOS|ANDROID|IOSROID|NA)", required = true)
    @NotNull(message = "支持系统类型不能为空")
    private OSCategory os;

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
}
