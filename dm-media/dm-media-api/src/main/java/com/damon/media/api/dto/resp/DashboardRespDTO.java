package com.damon.media.api.dto.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Damon S.
 */
@ToString
@Data
@NoArgsConstructor
@ApiModel(value = "物料数据查询请求")
public final class DashboardRespDTO implements Serializable {
    private static final long serialVersionUID = 752106L;

}
