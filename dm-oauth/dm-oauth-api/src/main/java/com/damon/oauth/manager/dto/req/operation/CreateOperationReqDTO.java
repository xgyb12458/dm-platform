package com.damon.oauth.manager.dto.req.operation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * 创建操作
 * @author Damon S.
 */
@Data
@ApiModel(value = "创建操作域所需参数")
public class CreateOperationReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "操作域编码", required = true)
    @NotNull(message = "操作域编码不能为空")
    @Pattern(regexp = "^(op_)\\w{1,12}$", message = "操作域编码应为15个以内英文数字下划线组合")
    private String code;


    @ApiModelProperty(value = "操作域名称", required = true)
    @NotNull(message = "操作域名称不能为空")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{1,10}", message = "操作域名称应为10个以内汉字")
    private String name;


    @ApiModelProperty(value = "所属平台", required = true)
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{1,10}", message = "平台名称应为10个以内汉字")
    private Integer platform;


    @ApiModelProperty(value = "操作域排序")
    private Integer sort;
}

