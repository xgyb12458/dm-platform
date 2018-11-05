package com.damon.order.api.dto.resp.trade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 确认订单返回数据
 * @author Damon S.
 */
@Data
@ToString
@ApiModel(value = "确认订单返回数据")
public class ConfirmOrderRespDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private String userId;


    private String ddd;
}
