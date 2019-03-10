package com.damon.shared.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 请求验签
 * @author Damon S.
 */
@Data
@ApiModel(value = "请求验签参数")
public class SecurityReqDTO {

    @ApiModelProperty(value = "验签字符")
    private String sign;

    @ApiModelProperty(value = "请求发起时间(毫秒)")
    @Min(0)
    private Long nonce;
}
