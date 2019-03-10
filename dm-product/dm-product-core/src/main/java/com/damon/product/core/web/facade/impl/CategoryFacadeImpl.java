package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.category.CreateCategoryReqDTO;
import com.damon.product.api.dto.req.category.QueryCategoryReqDTO;
import com.damon.product.api.dto.req.category.UpdateCategoryReqDTO;
import com.damon.product.api.dto.resp.category.CategoryInfoRespDTO;
import com.damon.product.api.web.facade.CategoryFacade;
import com.damon.product.core.query.handler.category.CategoryTranslator;
import com.damon.product.domain.category.aggregate.CategoryId;
import com.damon.product.domain.category.command.*;
import com.damon.product.domain.category.entity.CategoryEntry;
import com.damon.shared.common.Pagination;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import com.querydsl.core.QueryResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * 商品类别管理接口
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:44
 */
@Api(tags = "品类管理")
@RestController
@RequiredArgsConstructor
public class CategoryFacadeImpl implements CategoryFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final CategoryTranslator translator;


    @ArgsValid
    @Override
    @ApiOperation(value = "创建品类", notes = "创建品类")
    public ResponseWrapper<Long> create(
            CreateCategoryReqDTO createCategoryReqDTO) {
        CategoryId createdSpuId = commandGateway.sendAndWait(
                translator.translateFromReqDTO(createCategoryReqDTO)
        );
        return new ResponseWrapper<>(createdSpuId.getValue());
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "查询品类", notes = "查询品类")
    public ResponseWrapper<Pagination<CategoryInfoRespDTO>> query(
            QueryCategoryReqDTO queryCategoryReqDTO) {
        QueryCategoryCommand command = translator.translateFromReqDTO(queryCategoryReqDTO);
        CompletableFuture<QueryResults> futureResults =
                queryGateway.query(command, QueryResults.class);

        QueryResults<CategoryEntry> queryResults;
        try {
            queryResults = (QueryResults<CategoryEntry>) futureResults.get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }

        Pagination<CategoryInfoRespDTO> categoryInfoRespDTOs = new Pagination<>(
                queryResults.getOffset(),
                queryResults.getLimit(),
                queryResults.getTotal(),
                translator.translateToRespDTOs(queryResults)
        );
        return new ResponseWrapper<>(categoryInfoRespDTOs);
    }


    @ArgsValid
    @Override
    @ApiOperation(value = "更新品类", notes = "更新品类")
    public ResponseWrapper<Boolean> update(
            UpdateCategoryReqDTO updateCategoryReqDTO) {
        commandGateway.sendAndWait(
                translator.translateFromReqDTO(updateCategoryReqDTO)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "获取信息", notes = "获取信息")
    public ResponseWrapper<CategoryInfoRespDTO> find(Long categoryId) {
        CompletableFuture<CategoryEntry> futureResult =
                queryGateway.query(new FindCategoryByIdCommand(categoryId), CategoryEntry.class);

        CategoryEntry foundResult;
        try {
            foundResult = futureResult.get();
        } catch (Exception e) {
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }
        return new ResponseWrapper<>(
                translator.translateToRespDTO(foundResult)
        );
    }


    @Override
    @ApiOperation(value = "删除品类", notes = "删除指定品类")
    public ResponseWrapper<Boolean> remove(Long categoryId) {
        final long currentUserId = 1L;

        commandGateway.sendAndWait(
                new RemoveCategoryCommand(new CategoryId(categoryId), currentUserId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "恢复品类", notes = "恢复指定品类")
    public ResponseWrapper<Boolean> recover(Long categoryId) {
        final long currentUserId = 1L;

        commandGateway.sendAndWait(
                new RecoverCategoryCommand(new CategoryId(categoryId), currentUserId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "改变导航栏显示状态", notes = "改变导航栏显示状态")
    public ResponseWrapper<Boolean> changeNavState(Long categoryId) {
        final long currentUserId = 1L;

        commandGateway.sendAndWait(
                new ChangeCategoryNavStateCommand(new CategoryId(categoryId), currentUserId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }


    @Override
    @ApiOperation(value = "改变显示状态", notes = "改变显示状态")
    public ResponseWrapper<Boolean> changeShowState(Long categoryId) {
        final long currentUserId = 1L;

        commandGateway.sendAndWait(
                new ChangeCategoryShowStateCommand(new CategoryId(categoryId), currentUserId)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }
}
