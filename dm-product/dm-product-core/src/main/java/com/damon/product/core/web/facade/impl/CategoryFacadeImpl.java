package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.resp.category.CategoryInfoRespDTO;
import com.damon.product.api.dto.req.category.QueryCategoryReqDTO;
import com.damon.product.api.dto.req.category.UpdateCategoryReqDTO;
import com.damon.product.api.dto.req.spu.CreateSpuReqDTO;
import com.damon.product.api.web.facade.CategoryFacade;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品类别管理接口
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:44
 */
@Api(tags = "商品类别管理接口")
@RestController
@RequiredArgsConstructor
public class CategoryFacadeImpl implements CategoryFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Override
    public ResponseWrapper<Boolean> create(CreateSpuReqDTO createSpuReqDTO) {
        return null;
    }

    @Override
    public ResponseWrapper<List<CategoryInfoRespDTO>> query(QueryCategoryReqDTO queryCategoryReqDTO) {
        return null;
    }

    @Override
    public ResponseWrapper<Boolean> update(UpdateCategoryReqDTO updateCategoryReqDTO) {
        return null;
    }

    @Override
    public ResponseWrapper<CategoryInfoRespDTO> find(Long categoryId) {
        return null;
    }

    @Override
    public ResponseWrapper<Boolean> remove(Long categoryId) {
        return null;
    }
}
