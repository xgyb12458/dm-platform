package com.damon.shared.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 请求验签
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "请求验签参数")
public class SecurityReqDTO {

    @ApiModelProperty(name = "sign", value = "验签字符")
    private String sign;

    @ApiModelProperty(name = "nonce", value = "请求发起时间(毫秒)")
    private Long nonce;
}
