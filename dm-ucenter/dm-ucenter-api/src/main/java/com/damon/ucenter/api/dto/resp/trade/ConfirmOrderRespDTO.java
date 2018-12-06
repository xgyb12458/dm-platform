package com.damon.ucenter.api.dto.resp.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 确认订单返回数据
 * @author Damon S.
 */
@Data
@ToString
@Builder
@ApiModel(value = "确认订单返回数据")
public class ConfirmOrderRespDTO implements Serializable {
    private static final Long serialVersionUID = 21L;

    @ApiModelProperty(name = "address", value = "投递地址")
    private AddressInfoRespDTO address;


    private String ddd;
}
