package com.damon.product.core.query.handler.brand;

import com.damon.product.api.dto.req.brand.CreateBrandReqDTO;
import com.damon.product.api.dto.req.brand.QueryBrandReqDTO;
import com.damon.product.api.dto.req.brand.UpdateBrandReqDTO;
import com.damon.product.api.dto.resp.brand.BrandInfoRespDTO;
import com.damon.product.domain.brand.aggregate.BrandId;
import com.damon.product.domain.brand.command.CreateBrandCommand;
import com.damon.product.domain.brand.command.QueryBrandCommand;
import com.damon.product.domain.brand.command.UpdateBrandCommand;
import com.damon.product.domain.brand.entity.BrandEntry;
import com.damon.shared.enums.YesNoEnum;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 品牌管理功能适配器
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 18:04
 */
@Component
public final class BrandTranslator {

    /**
     * 转换Brand对象
     */
    public BrandInfoRespDTO translateToRespDTO(BrandEntry brandEntry) {
        BrandInfoRespDTO brandInfoRespDTO = new BrandInfoRespDTO();
        BeanUtils.copyProperties(brandEntry, brandInfoRespDTO);

        // 处理其它未自动赋值的字段
//        brandInfoRespDTO.setNavState(YesNoEnum.codeOf(brandEntry.getNavState()));
//        brandInfoRespDTO.setShowState(YesNoEnum.codeOf(brandEntry.getShowState()));
        brandInfoRespDTO.setCreatedAt(brandEntry.getCreatedAt().getTime());
        brandInfoRespDTO.setUpdatedAt(brandEntry.getUpdatedAt().getTime());

        return brandInfoRespDTO;
    }


    /**
     * 转换Category对象列表
     */
    public List<BrandInfoRespDTO> translateToRespDTOs(
            QueryResults<BrandEntry> entries) {
        return entries.getResults().stream()
                .map(this::translateToRespDTO)
                .collect(Collectors.toList());
    }


    /**
     * 转换为更新命令
     */
    public UpdateBrandCommand translateFromReqDTO(UpdateBrandReqDTO reqDTO) {
        Long currentUserId = 10000L;
        UpdateBrandCommand.UpdateBrandCommandBuilder builder = UpdateBrandCommand.builder();

        Optional.ofNullable(reqDTO.getName()).ifPresent(builder::name);
        Optional.ofNullable(reqDTO.getCode()).ifPresent(builder::code);
        Optional.ofNullable(reqDTO.getSort()).ifPresent(builder::sort);
        Optional.ofNullable(reqDTO.getHomepage()).ifPresent(builder::homepage);
        Optional.ofNullable(reqDTO.getLogo()).ifPresent(builder::logo);
        Optional.ofNullable(reqDTO.getBigImage()).ifPresent(builder::bigImage);
        Optional.ofNullable(reqDTO.getDescription()).ifPresent(builder::description);
        Optional.ofNullable(reqDTO.getBrandId()).ifPresent(
                brandId -> builder.brandId(new BrandId(brandId))
        );
        Optional.ofNullable(reqDTO.getFirstLetter()).ifPresent(
                letter -> builder.firstLetter(letter.toUpperCase())
        );
        Optional.ofNullable(reqDTO.getDisplay()).ifPresent(
                display -> builder.display(YesNoEnum.parse(display))
        );
        Optional.ofNullable(reqDTO.getFactoryState()).ifPresent(
                state -> builder.factoryState(YesNoEnum.parse(state))
        );
        return builder.updatedBy(currentUserId).build();
    }

    /**
     * 转换为查询命命令
     */
    public QueryBrandCommand translateFromReqDTO(QueryBrandReqDTO reqDTO) {
        QueryBrandCommand.QueryBrandCommandBuilder builder = QueryBrandCommand.builder();

        Optional.ofNullable(reqDTO.getName()).ifPresent(builder::name);
        Optional.ofNullable(reqDTO.getCode()).ifPresent(builder::code);
        Optional.ofNullable(reqDTO.getFactoryState()).ifPresent(
                state -> builder.factoryState(YesNoEnum.parse(state))
        );
        Optional.ofNullable(reqDTO.getRemoved()).ifPresent(
                removed -> builder.removed(YesNoEnum.parse(removed))
        );
        Optional.ofNullable(reqDTO.getCreatedFrom()).ifPresent(
                from -> builder.createdFrom(Instant.ofEpochMilli(from))
        );
        Optional.ofNullable(reqDTO.getFirstLetter()).ifPresent(
                letter -> builder.firstLetter(letter.toUpperCase())
        );
        Optional.ofNullable(reqDTO.getCreatedTo()).ifPresent(
                to -> builder.createdFrom(Instant.ofEpochMilli(to))
        );
        Optional.ofNullable(reqDTO.getPageSize()).ifPresent(builder::pageSize);
        Optional.ofNullable(reqDTO.getPageIndex()).ifPresent(builder::pageIndex);

//        if () {
//            builder.createdBy(createdBy);
//        }
        return builder.build();
    }


    /**
     * 转换为创建命令
     */
    public CreateBrandCommand translateFromReqDTO(CreateBrandReqDTO reqDTO) {
        Long currentUserId = 10000L;
        CreateBrandCommand.CreateBrandCommandBuilder builder = CreateBrandCommand.builder();

        Optional.ofNullable(reqDTO.getName()).ifPresent(builder::name);
        Optional.ofNullable(reqDTO.getCode()).ifPresent(builder::code);
        Optional.ofNullable(reqDTO.getBigImage()).ifPresent(builder::bigImage);
        Optional.ofNullable(reqDTO.getDescription()).ifPresent(builder::description);
        Optional.ofNullable(reqDTO.getHomepage()).ifPresent(builder::homepage);
        Optional.ofNullable(reqDTO.getSort()).ifPresent(builder::sort);
        Optional.ofNullable(reqDTO.getLogo()).ifPresent(builder::logo);
        Optional.ofNullable(reqDTO.getFactoryState()).ifPresent(
                state -> builder.factoryState(YesNoEnum.parse(state))
        );
        Optional.ofNullable(reqDTO.getDisplay()).ifPresent(
                display -> builder.display(YesNoEnum.parse(display))
        );
        Optional.ofNullable(reqDTO.getFirstLetter()).ifPresent(
                letter -> builder.firstLetter(letter.toUpperCase())
        );
        return builder.brandId(new BrandId()).createdBy(currentUserId).build();
    }
}
