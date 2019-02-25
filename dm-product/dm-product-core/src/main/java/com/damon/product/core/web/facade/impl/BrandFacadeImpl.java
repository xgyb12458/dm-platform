package com.damon.product.core.web.facade.impl;

import com.damon.product.api.dto.req.brand.CreateBrandReqDTO;
import com.damon.product.api.dto.req.brand.QueryBrandReqDTO;
import com.damon.product.api.dto.req.brand.UpdateBrandReqDTO;
import com.damon.product.api.dto.resp.brand.BrandInfoRespDTO;
import com.damon.product.api.web.facade.BrandFacade;
import com.damon.product.core.query.handler.brand.BrandAdapter;
import com.damon.product.domain.brand.command.*;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.enums.YesNoEnum;
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

        Long currentUserId = 1L;
        CreateBrandCommand command = CreateBrandCommand.builder()
                .name(createBrandReqDTO.getName())
                .code(createBrandReqDTO.getCode())
                .firstLetter(createBrandReqDTO.getFirstLetter())
                .sort(createBrandReqDTO.getSort())
                .logo(createBrandReqDTO.getLogo())
                .bigImage(createBrandReqDTO.getBigImage())
                .display(YesNoEnum.parse(createBrandReqDTO.getDisplay()))
                .factoryState(YesNoEnum.parse(createBrandReqDTO.getFactoryState()))
                .brandStory(createBrandReqDTO.getBrandStory())
                .createdBy(currentUserId)
                .build();

        // 传达创建品牌命令
        Long brandId = commandGateway.sendAndWait(command);
        return new ResponseWrapper<>(brandId > 0);
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "查询品牌", notes = "查询品牌")
    public ResponseWrapper<List<BrandInfoRespDTO>> query(QueryBrandReqDTO queryBrandReqDTO) {
        Long currentUserId = 1L;
        QueryBrandCommand command = QueryBrandCommand.builder()
                .name(queryBrandReqDTO.getName())
                .code(queryBrandReqDTO.getCode())
                .firstLetter(queryBrandReqDTO.getFirstLetter())
                .display(YesNoEnum.parse(queryBrandReqDTO.getDisplay()))
                .factoryState(YesNoEnum.parse(queryBrandReqDTO.getFactoryState()))
                .createdBy(currentUserId)
                .createdFrom(queryBrandReqDTO.getCreatedFrom())
                .createdTo(queryBrandReqDTO.getCreatedTo())
                .build();

        queryGateway.query(command, BrandInfoRespDTO.class);
        return new ResponseWrapper<>();
    }

    @ArgsValid
    @Override
    @ApiOperation(value = "编辑品牌信息", notes = "编辑品牌信息")
    public ResponseWrapper<Boolean> update(UpdateBrandReqDTO updateBrandReqDTO) {
        Long currentUserId = 1L;
        UpdateBrandCommand command = UpdateBrandCommand.builder()
                .brandId(updateBrandReqDTO.getBrandId())
                .name(updateBrandReqDTO.getName())
                .code(updateBrandReqDTO.getCode())
                .firstLetter(updateBrandReqDTO.getFirstLetter())
                .sort(updateBrandReqDTO.getSort())
                .logo(updateBrandReqDTO.getLogo())
                .bigImage(updateBrandReqDTO.getBigImage())
                .display(YesNoEnum.parse(updateBrandReqDTO.getDisplay()))
                .factoryState(YesNoEnum.parse(updateBrandReqDTO.getFactoryState()))
                .brandStory(updateBrandReqDTO.getBrandStory())
                .updatedBy(currentUserId)
                .build();

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "获取品牌信息", notes = "获取品牌信息")
    public ResponseWrapper<BrandInfoRespDTO> find(Long brandId) {
        QueryBrandCommand command = QueryBrandCommand.builder()
                .brandId(brandId)
                .build();

        queryGateway.query(command, BrandInfoRespDTO.class);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "删除品牌", notes = "删除品牌")
    public ResponseWrapper<Boolean> remove(Long brandId) {
        Long currentUserId = 1L;
        DeleteBrandCommand command = new DeleteBrandCommand(brandId, currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "恢复品牌", notes = "恢复品牌")
    public ResponseWrapper<Boolean> recover(Long brandId) {
        Long currentUserId = 1L;
        RecoverBrandCommand command = new RecoverBrandCommand(brandId, currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "更改品牌显示状态", notes = "更改品牌显示状态")
    public ResponseWrapper<Boolean> changeDisplayState(Long brandId) {
        Long currentUserId = 1L;
        ChangeBrandDisplayCommand command = new ChangeBrandDisplayCommand(brandId, currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }

    @Override
    @ApiOperation(value = "更改品牌制造商状态", notes = "更改品牌制造商状态")
    public ResponseWrapper<Boolean> changeFactoryState(Long brandId) {
        Long currentUserId = 1L;
        ChangeBrandFactoryCommand command = new ChangeBrandFactoryCommand(brandId, currentUserId);

        commandGateway.sendAndWait(command);
        return new ResponseWrapper<>();
    }
}
