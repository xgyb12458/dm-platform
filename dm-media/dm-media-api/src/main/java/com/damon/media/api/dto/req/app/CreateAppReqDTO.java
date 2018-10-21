package com.damon.media.api.dto.req.app;

import com.damon.media.shared.enums.MediaSource;
import com.damon.media.shared.enums.MediaType;
import com.damon.shared.enums.OSCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/***
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "新建媒体应用请求参数")
public final class CreateAppReqDTO implements Serializable {
    private static final long serialVersionUID = 22480613254L;


    @ApiModelProperty(name = "name", value = "应用名称", required = true)
    @NotNull(message = "应用名称不能为空")
    @Length(max = 30, message = "应用名称不得超过30个字符")
    private String name;


    @ApiModelProperty(name = "source", value = "媒体归属(ASSET|ALIEN)", required = true)
    @NotNull(message = "媒体归属不能为空")
    private MediaSource source;


    @ApiModelProperty(name = "type", value = "应用类型(APP|WAP|WEB|POS)", required = true)
    @NotNull(message = "应用类型不能为空")
    private MediaType type;


    @ApiModelProperty(name = "os", value = "应用的操作系统平台(IOS|ANDROID)", required = true)
    @NotNull(message = "操作系统不能为空")
    private OSCategory os;


    @ApiModelProperty(name = "downloadUrl", value = "应用下载地址", required = true)
    @NotNull(message = "应用下载链接不能为空")
    @URL(regexp = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$", message = "应用下载地址不合法")
    private String downloadUrl;
// String dddf ="^(https?://)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([/\\w \\.-]*)*\\/?$/";

    @ApiModelProperty(name = "packageName", value = "程序主包名称", required = true)
    @NotNull(message = "程序主包名称不能为空")
    private String packageName;


    @ApiModelProperty(name = "industry", value = "所属行业", required = true)
    @NotNull(message = "所属行业不能为空")
    private String industry;


    @ApiModelProperty(name = "snapshot", value = "APP示意图", required = true)
    @NotNull(message = "APP示意图不能为空")
    private String snapshot;


    @ApiModelProperty(name = "category", value = "应用人群兴趣分类")
    private String category;


    @ApiModelProperty(name = "keywords", value = "应用人群兴趣关键词，空格分隔，每个关键词不超过10个字")
    private String keywords;


    @ApiModelProperty(name = "description", value = "应用介绍")
    private String description;


    @ApiModelProperty(name = "userId", value = "应用所属用户")
    private Long userId;
}
