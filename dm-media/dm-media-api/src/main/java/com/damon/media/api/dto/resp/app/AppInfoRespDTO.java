package com.damon.media.api.dto.resp.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Damon S.
 */
@ToString
@Data
@NoArgsConstructor
@ApiModel(value = "查询App的响应信息")
public class AppInfoRespDTO implements Serializable {
    private static final long serialVersionUID = 3221169057056L;

    @ApiModelProperty(value = "应用标识ID")
    private Long appId;

    @ApiModelProperty(value = "应用名称")
    private String name;

    @ApiModelProperty(value = "应用的操作系统平台")
    private String os;

    @ApiModelProperty(value = "appKey")
    private String appKey;

    @ApiModelProperty(value = "secret")
    private String secret;

    @ApiModelProperty(value = "应用类型")
    private String type;

    @ApiModelProperty(value = "媒体归属")
    private String source;

    @ApiModelProperty(value = "审核状态")
    private String status;

    @ApiModelProperty(value = "停启状态")
    private String state;

    @ApiModelProperty(value = "应用下载地址")
    private String downloadUrl;

    @ApiModelProperty(value = "程序主包名称")
    private String packageName;

    @ApiModelProperty(value = "所属行业")
    private String industry;

    @ApiModelProperty(value = "应用人群兴趣分类")
    private String category;

    @ApiModelProperty(value = "应用人群兴趣关键词")
    private String keywords;

    @ApiModelProperty(value = "应用介绍")
    private String description;

    @ApiModelProperty(value = "应用所属用户ID")
    private Long userId;

    @ApiModelProperty(value = "创建者标识")
    private Long createdBy;

    @ApiModelProperty(value = "最后更新者标识")
    private Long updatedBy;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "最后更新时间")
    private Timestamp updateTime;
}
