package com.damon.product.core.query.handler.category;

import com.damon.product.api.dto.req.category.CreateCategoryReqDTO;
import com.damon.product.api.dto.resp.category.CategoryInfoRespDTO;
import com.damon.product.domain.category.aggregate.CategoryId;
import com.damon.product.domain.category.command.CreateCategoryCommand;
import com.damon.product.domain.category.entity.CategoryEntry;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品类别实用工具
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 11:27
 */
@Component
public final class CategoryTranslator {


    /**
     * 转换Category对象
     */
    public CategoryInfoRespDTO translateToRespDTO(CategoryEntry categoryEntry) {
        CategoryInfoRespDTO categoryInfoRespDTO = new CategoryInfoRespDTO();
        BeanUtils.copyProperties(categoryEntry, categoryInfoRespDTO);

//        // 处理其它未自动赋值的字段
//        categoryInfoRespDTO.setType(ProductType.valueOf(categoryEntry.getType()));
//        categoryInfoRespDTO.setAlbumImages(Lists.newArrayList());
//        categoryInfoRespDTO.setSupportReturn(getCode(categoryEntry.getSupportReturn()));
//        categoryInfoRespDTO.setSoldOut(getCode(categoryEntry.getSoldOut()));
//        categoryInfoRespDTO.setNewProduct(getCode(categoryEntry.getNewProduct()));
//        categoryInfoRespDTO.setRecommended(getCode(categoryEntry.getRecommended()));
//        categoryInfoRespDTO.setSkus(Collections.emptyList());

        return categoryInfoRespDTO;
    }


    /**
     * 转换Category对象列表
     */
    public List<CategoryInfoRespDTO> translateToRespDTOs(
            QueryResults<CategoryEntry> entries) {
        return entries.getResults().stream()
                .map(this::translateToRespDTO)
                .collect(Collectors.toList());
    }


    /**
     * 转换为创建命令
     */
    public CreateCategoryCommand translateFromReqDTO(CreateCategoryReqDTO reqDTO) {
        Long currentUserId = 10000L;

        return CreateCategoryCommand.builder()
                .categoryId(new CategoryId())
                .name(reqDTO.getName())
                .createdBy(currentUserId)
                .description(reqDTO.getDescription())
                .icon(reqDTO.getIcon())
                .keywords(reqDTO.getKeywords())
                .level(reqDTO.getLevel())
                .navState(reqDTO.getNavState())
                .parentId(reqDTO.getParentId())
                .showState(reqDTO.getShowState())
                .sort(reqDTO.getSort())
                .spuCount(reqDTO.getSpuCount())
                .spuUnit(reqDTO.getSpuUnit())
                .createdBy(currentUserId)
                .build();
    }
}
