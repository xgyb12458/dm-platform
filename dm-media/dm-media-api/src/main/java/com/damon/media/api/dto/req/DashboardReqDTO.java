package com.damon.media.api.dto.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Damon S.
 */
@ToString
@Data
@ApiModel(value = "物料数据查询请求")
public final class DashboardReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

}
