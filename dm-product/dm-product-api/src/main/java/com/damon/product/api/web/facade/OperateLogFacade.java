package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.log.QueryOperateLogReqDTO;
import com.damon.product.api.dto.resp.log.OperateLogRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月12日 09:27
 */
@RequestMapping(value = ApiConstants.SERVE_NAME + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface OperateLogFacade {

    @PostMapping("/logs/query")
    ResponseWrapper<Pagination<OperateLogRespDTO>> query(
            @RequestBody @Validated QueryOperateLogReqDTO queryOperateLogReqDTO
    );
}
