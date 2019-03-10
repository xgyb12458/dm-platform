package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.brand.CreateBrandReqDTO;
import com.damon.product.api.dto.req.brand.QueryBrandReqDTO;
import com.damon.product.api.dto.req.brand.UpdateBrandReqDTO;
import com.damon.product.api.dto.resp.brand.BrandInfoRespDTO;
import com.damon.product.api.web.facade.BrandFacade;
import com.damon.product.core.query.handler.brand.BrandTranslator;
import com.damon.product.domain.brand.aggregate.BrandId;
import com.damon.product.domain.brand.command.*;
import com.damon.product.domain.brand.entity.BrandEntry;
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
 * 品牌管理接口
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:44
 */
@Api(tags = "品牌管理")
@RestController
@RequiredArgsConstructor
public class BrandFacadeImpl implements BrandFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final BrandTranslator translator;

    @ArgsValid
    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<Long> create(CreateBrandReqDTO createBrandReqDTO) {
//        if (!BrandTranslator.checkParameter(createBrandReqDTO)) {
//            return new ResponseWrapper<>(ResponseCodeEnum.BAD_REQUEST);
//        }

        Long createdBrandId = commandGateway.sendAndWait(
                translator.translateFromReqDTO(createBrandReqDTO)
        );
        return new ResponseWrapper<>(createdBrandId);
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "查询品牌", notes = "查询品牌")
    public ResponseWrapper<Pagination<BrandInfoRespDTO>> query(
            QueryBrandReqDTO queryBrandReqDTO) {
        CompletableFuture<QueryResults> futureResults = queryGateway.query(
                translator.translateFromReqDTO(queryBrandReqDTO),
                QueryResults.class
        );

        QueryResults<BrandEntry> queryResults;
        try {
            queryResults = (QueryResults<BrandEntry>) futureResults.get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseWrapper<>(ResponseCodeEnum.INTERNAL_ERROR);
        }

        Pagination<BrandInfoRespDTO> brandInfoRespDTOs = new Pagination<>(
                queryResults.getOffset(),
                queryResults.getLimit(),
                queryResults.getTotal(),
                translator.translateToRespDTOs(queryResults)
        );
        return new ResponseWrapper<>(brandInfoRespDTOs);
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "编辑品牌信息", notes = "编辑品牌信息")
    public ResponseWrapper<Boolean> update(UpdateBrandReqDTO updateBrandReqDTO) {
        commandGateway.sendAndWait(
                translator.translateFromReqDTO(updateBrandReqDTO)
        );
        return new ResponseWrapper<>(Boolean.TRUE);
    }

    @Override
    @ApiOperation(value = "获取品牌信息", notes = "获取品牌信息")
    public ResponseWrapper<BrandInfoRespDTO> find(Long brandId) {
        CompletableFuture<BrandEntry> futureResult =
                queryGateway.query(new FindBrandByIdCommand(brandId), BrandEntry.class);

        BrandEntry foundResult;
        try {
            foundResult = futureResult.get();
        } catch (Exception e) {
            return new ResponseWrapper<>(ResponseCodeEnum.NOT_FOUND);
        }
        return new ResponseWrapper<>(
                translator.translateToRespDTO(foundResult)
        );
    }

    @Override
    @ApiOperation(value = "删除品牌", notes = "删除品牌")
    public ResponseWrapper<Boolean> remove(Long brandId) {
        Long currentUserId = 1L;

        commandGateway.sendAndWait(
                new RemoveBrandCommand(new BrandId(brandId), currentUserId)
        );
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "恢复品牌", notes = "恢复品牌")
    public ResponseWrapper<Boolean> recover(Long brandId) {
        Long currentUserId = 1L;

        commandGateway.sendAndWait(
                new RecoverBrandCommand(new BrandId(brandId), currentUserId)
        );
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "更改品牌显示状态", notes = "更改品牌显示状态")
    public ResponseWrapper<Boolean> changeDisplayState(Long brandId) {
        Long currentUserId = 1L;

        commandGateway.sendAndWait(
                new ChangeBrandDisplayCommand(new BrandId(brandId), currentUserId)
        );
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "更改品牌制造商状态", notes = "更改品牌制造商状态")
    public ResponseWrapper<Boolean> changeFactoryState(Long brandId) {
        Long currentUserId = 1L;

        commandGateway.sendAndWait(
                new ChangeBrandFactoryCommand(new BrandId(brandId), currentUserId)
        );
        return new ResponseWrapper<>();
    }
}
