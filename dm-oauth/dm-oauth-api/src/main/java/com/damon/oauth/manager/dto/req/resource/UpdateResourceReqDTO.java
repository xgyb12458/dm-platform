package com.damon.oauth.manager.dto.req.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * 更新资源域
 * @author Damon S.
 */
@Data
@ApiModel(value = "创建资源所需参数")
public class UpdateResourceReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源域ID", required = true)
    @NotNull(message = "资源域编号不能为空")
    private Long resourceId;

    @ApiModelProperty(value = "父资源域编号")
    @NotNull(message = "父资源域编号不能为空")
    private Long parentId;


    @ApiModelProperty(value = "资源名称", required = true)
    @NotNull(message = "资源域名称不能为空")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{1,10}", message = "资源名称应为10个以内汉字")
    private String name;


    @ApiModelProperty(value = "资源路径", required = true)
    @NotNull(message = "资源路径不能为空")
    @Pattern(regexp = "^\\w{1,15}$", message = "资源编码应为15个以内英文数字组合")
    private String path;


    @ApiModelProperty(value = "资源域排序")
    private Integer sort;


    @ApiModelProperty(value = "资源域所属平台")
    private String platform;
}

