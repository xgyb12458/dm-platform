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

    @ApiModelProperty(name = "appIds", value = "应用标识ID")
    private Long appId;

    @ApiModelProperty(name = "name", value = "应用名称")
    private String name;

    @ApiModelProperty(name = "os", value = "应用的操作系统平台")
    private String os;

    @ApiModelProperty(name = "appKey", value = "appKey")
    private String appKey;

    @ApiModelProperty(name = "secret", value = "secret")
    private String secret;

    @ApiModelProperty(name = "type", value = "应用类型")
    private String type;

    @ApiModelProperty(name = "source", value = "媒体归属")
    private String source;

    @ApiModelProperty(name = "status", value = "审核状态")
    private String status;

    @ApiModelProperty(name = "state", value = "停启状态")
    private String state;

    @ApiModelProperty(name = "downloadUrl", value = "应用下载地址")
    private String downloadUrl;

    @ApiModelProperty(name = "packageName", value = "程序主包名称")
    private String packageName;

    @ApiModelProperty(name = "industry", value = "所属行业")
    private String industry;

    @ApiModelProperty(name = "category", value = "应用人群兴趣分类")
    private String category;

    @ApiModelProperty(name = "keywords", value = "应用人群兴趣关键词")
    private String keywords;

    @ApiModelProperty(name = "description", value = "应用介绍")
    private String description;

    @ApiModelProperty(name = "userId", value = "应用所属用户ID")
    private Long userId;

    @ApiModelProperty(name = "createdBy", value = "创建者标识")
    private Long createdBy;

    @ApiModelProperty(name = "updatedBy", value = "最后更新者标识")
    private Long updatedBy;

    @ApiModelProperty(name = "createdAt", value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(name = "updateTIme", value = "最后更新时间")
    private Timestamp updateTime;
}
