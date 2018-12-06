package com.damon.oauth.api.dto.req.tenant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * 用户登录请求
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "用户登录请求")
public class CreateTenantReqDTO implements Serializable {

    private static final Long serialVersionUID = 12237484090L;


    @ApiModelProperty(name = "code", value = "租户编码", required = true)
    @NotNull(message = "请输入租户编码")
    @Pattern(regexp = "^\\w{1,15}$", message = "角色编码应为15个以内英文数字组合")
    private String code;


    @ApiModelProperty(name = "name", value = "租户名称", required = true)
    @NotNull(message = "请输入租户名称")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{1,10}", message = "租户名称应为10个以内汉字")
    private String name;

}

