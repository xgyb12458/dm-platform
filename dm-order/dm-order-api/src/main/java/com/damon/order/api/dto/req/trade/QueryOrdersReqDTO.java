package com.damon.order.api.dto.req.trade;

import com.damon.shared.dto.PageableReqDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询订单信息参数
 * @author Damon S.
 */
@Data
@ApiModel(value = "查询订单信息参数")
public class QueryOrdersReqDTO extends PageableReqDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

}
