package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.brand.CreateBrandReqDTO;
import com.damon.product.api.dto.req.brand.QueryBrandReqDTO;
import com.damon.product.api.dto.req.brand.UpdateBrandReqDTO;
import com.damon.product.api.dto.resp.brand.BrandInfoRespDTO;
import com.damon.product.api.web.facade.BrandFacade;
import com.damon.product.core.query.handler.brand.BrandAdapter;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.validation.ArgsValid;
import com.damon.shared.wrapper.ResponseWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌管理接口
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 17:44
 */
@Api(tags = "品牌管理接口")
@RestController
@RequiredArgsConstructor
public class BrandFacadeImpl implements BrandFacade {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @ArgsValid
    @Override
    @ApiOperation(value = "创建品牌", notes = "创建品牌")
    public ResponseWrapper<Boolean> create(CreateBrandReqDTO createBrandReqDTO) {
        if (!BrandAdapter.checkParameter(createBrandReqDTO)) {
            return new ResponseWrapper<>(ResponseCodeEnum.BAD_REQUEST);
        }

        Long brandId = commandGateway.sendAndWait(
                BrandAdapter.transformCommand(createBrandReqDTO)
        );
        return new ResponseWrapper<>(brandId > 0);
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "查询品牌", notes = "查询品牌")
    public ResponseWrapper<List<BrandInfoRespDTO>> query(QueryBrandReqDTO queryBrandReqDTO) {
        return null;
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "编辑品牌信息", notes = "编辑品牌信息")
    public ResponseWrapper<Boolean> update(UpdateBrandReqDTO updateBrandReqDTO) {
        return null;
    }

    @Override
    @ApiOperation(value = "获取品牌信息", notes = "获取品牌信息")
    public ResponseWrapper<BrandInfoRespDTO> find(Long brandId) {
        return null;
    }

    @Override
    @ApiOperation(value = "更改品牌显示状态", notes = "更改品牌显示状态")
    public ResponseWrapper<Boolean> changeDisplayState(Long brandId) {
        return null;
    }

    @Override
    @ApiOperation(value = "更改品牌制造商状态", notes = "更改品牌制造商状态")
    public ResponseWrapper<Boolean> changeFactoryState(Long brandId) {
        return null;
    }

    @Override
    @ApiOperation(value = "删除品牌", notes = "删除品牌")
    public ResponseWrapper<Boolean> remove(Long brandId) {
        return null;
    }
}
