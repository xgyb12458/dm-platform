package com.damon.media.api.dto.req.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/***
 * 更新媒体应用请求
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "媒体应用属性编辑参数")
public final class UpdateAppReqDTO implements Serializable {
    private static final long serialVersionUID = 7247714666080613254L;

    @ApiModelProperty(name = "appIds", value = "应用标识ID", required = true)
    @NotNull(message = "应用标识ID不能为空")
    @Min(value = 100000000000000L, message = "应用标识不合法")
    private Long appId;


    @ApiModelProperty(name = "industry", value = "所属行业")
    private String industry;


    @ApiModelProperty(name = "category", value = "应用人群兴趣分类")
    private String category;


    @ApiModelProperty(name = "keywords", value = "应用人群兴趣关键词，空格分隔，每个关键词不超过10个字")
    private String keywords;


    @ApiModelProperty(name = "snapshot", value = "APP示意图")
    private String snapshot;


    @ApiModelProperty(name = "description", value = "应用介绍")
    private String description;
}
