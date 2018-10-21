package com.damon.order.api.dto.req.operation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * 创建订单请求参数
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "创建订单所需参数")
public class CreateOrderReqDTO implements Serializable {

    private static final Long serialVersionUID = 1L;


    @ApiModelProperty(name = "code", value = "操作编码", required = true)
    @NotNull(message = "请输入操作编码")
    @Pattern(regexp = "^\\w{1,15}$", message = "操作编码应为15个以内英文数字组合")
    private String code;


    @ApiModelProperty(name = "name", value = "操作名称", required = true)
    @NotNull(message = "请输入操作名称")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{1,10}", message = "操作名称应为10个以内汉字")
    private String name;
}

