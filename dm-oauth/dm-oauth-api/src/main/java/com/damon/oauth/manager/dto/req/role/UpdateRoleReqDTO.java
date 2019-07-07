package com.damon.oauth.manager.dto.req.role;

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
@ApiModel(value = "创建角色所需参数")
public class UpdateRoleReqDTO implements Serializable {
    private static final Long serialVersionUID = 92217937484090L;

    @ApiModelProperty(value = "角色编码", required = true)
    @NotNull(message = "请输入角色编码")
    @Pattern(regexp = "^\\w{1,15}$", message = "角色编码应为15个以内英文数字组合")
    private String code;


    @ApiModelProperty(value = "角色名称", required = true)
    @NotNull(message = "请输入角色名称")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{1,10}", message = "角色名称应为10个以内汉字")
    private String name;

}

