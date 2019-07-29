package com.damon.oauth.manager.dto.req.operation;

import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * 查询操作域
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "创建操作所需参数")
public class QueryOperationReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;


    @ApiModelProperty(value = "操作域编号", required = true)
    @NotNull(message = "请输入操作域编号")
    private Long operationId;


    @ApiModelProperty(value = "操作域编码")
    @NotNull(message = "请输入操作编码")
    @Pattern(regexp = "^\\w{1,15}$", message = "操作编码应为15个以内英文数字组合")
    private String code;


    @ApiModelProperty(value = "操作域名称")
    @NotNull(message = "请输入操作名称")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{1,10}", message = "操作名称应为10个以内汉字")
    private String name;
}

