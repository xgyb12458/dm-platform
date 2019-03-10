package com.damon.media.api.dto.req.slot;

import com.damon.media.api.dto.req.PageableReqDTO;
import com.damon.shared.enums.AuditStatus;
import com.damon.shared.enums.OSCategory;
import com.damon.media.shared.enums.SlotType;
import com.damon.shared.enums.SwitchState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "资源位查询参数")
public final class QuerySlotReqDTO extends PageableReqDTO implements Serializable {
    private static final long serialVersionUID = 1109696721536L;

    @ApiModelProperty(value = "应用ID")
    @Min(value = 100000000000000L, message = "应用标识不合法")
    private Long appId;

    @ApiModelProperty(value = "资源位名称")
    @Length(max = 30, message = "资源位名称不得超过30个字符")
    private String name;

    @ApiModelProperty(value = "资源位别名")
    @Length(max = 64, message = "资源位名称不得超过64个字符")
    private String alias;

    @ApiModelProperty(value = "资源位规格ID")
    @Min(value = 100000000000000L, message = "资源位标识不合法")
    private Long specId;

    @ApiModelProperty(value = "资源位类型(SPLASH|BULLET|BANNER|FEEDS)")
    private SlotType type;

    @ApiModelProperty(value = "支持系统类型(IOS|ANDROID|IOSROID|NA)")
    private OSCategory os;

    @ApiModelProperty(value = "资源位所属频道")
    private String channel;

    @ApiModelProperty(value = "所属用户Id")
    private Long userId;

    @ApiModelProperty(value = "启停状态")
    private SwitchState state;

    @ApiModelProperty(value = "审核状态")
    private AuditStatus status;
}
