package com.damon.product.core.query.handler.spu;

import com.damon.product.api.dto.req.spu.CreateSpuReqDTO;
import com.damon.product.api.dto.req.spu.QuerySpuReqDTO;
import com.damon.product.api.dto.req.spu.UpdateSpuReqDTO;
import com.damon.product.api.dto.resp.spu.SpuInfoRespDTO;
import com.damon.product.domain.sku.aggregate.ProductSku;
import com.damon.product.domain.spu.aggregate.SpuId;
import com.damon.product.domain.spu.command.CreateSpuCommand;
import com.damon.product.domain.spu.command.QuerySpuCommand;
import com.damon.product.domain.spu.command.UpdateSpuCommand;
import com.damon.product.domain.spu.entity.SpuEntry;
import com.damon.product.shared.enums.ProductType;
import com.damon.product.shared.enums.SpuState;
import com.damon.product.shared.enums.VerifyState;
import com.damon.shared.enums.YesNoEnum;
import com.google.common.collect.Lists;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SKU实用工具
 * @author Damon S.
 */
@Component
public final class SpuTranslator {

    /**
     * 转换SPU对象
     */
    public SpuInfoRespDTO translateToRespDTO(SpuEntry spuEntry) {
        SpuInfoRespDTO spuInfoRespDTO = new SpuInfoRespDTO();
        BeanUtils.copyProperties(spuEntry, spuInfoRespDTO);

        // 处理其它未自动赋值的字段
        spuInfoRespDTO.setType(ProductType.valueOf(spuEntry.getType()));
        // TODO:
        spuInfoRespDTO.setAlbumImages(Lists.newArrayList());
        spuInfoRespDTO.setSupportReturn(YesNoEnum.codeOf(spuEntry.getSupportReturn()));
        spuInfoRespDTO.setSoldOut(YesNoEnum.codeOf(spuEntry.getSoldOut()));
        spuInfoRespDTO.setNewProduct(YesNoEnum.codeOf(spuEntry.getNewProduct()));
        spuInfoRespDTO.setRecommended(YesNoEnum.codeOf(spuEntry.getRecommended()));
        // TODO:
        spuInfoRespDTO.setSkus(Collections.emptyList());

        return spuInfoRespDTO;
    }


    /**
     * 转换SPU对象列表
     */
    public List<SpuInfoRespDTO> translateToRespDTOs(QueryResults<SpuEntry> spuEntries) {
        List<SpuInfoRespDTO> spuInfoRespDTOs = new ArrayList<>(spuEntries.getResults().size());
        spuEntries.getResults().forEach(
                entry -> spuInfoRespDTOs.add(translateToRespDTO(entry))
        );
        return spuInfoRespDTOs;
    }

    /**
     * 转换为更新命令
     */
    public UpdateSpuCommand translateFromReqDTO(UpdateSpuReqDTO reqDTO) {
        return UpdateSpuCommand.builder()
                .spuId(new SpuId(reqDTO.getSpuId()))
                .name(reqDTO.getName())
                .build();
    }

    /**
     * 转换为查询命令
     */
    public QuerySpuCommand translateFromReqDTO(QuerySpuReqDTO reqDTO) {
        return QuerySpuCommand.builder()
                .name(reqDTO.getName())
                .spuCode(reqDTO.getSpuCode())
                .state(SpuState.parse(reqDTO.getState()))
                .verifyState(VerifyState.parse(reqDTO.getVerifyState()))
//                .newProduct(YesNoEnum.parse(querySpuReqDTO.getNewProduct()))
//                .recommended(YesNoEnum.parse(querySpuReqDTO.getRecommended()))
//                .soldOut(YesNoEnum.parse(querySpuReqDTO.getSoldOut()))
                .supportReturn(YesNoEnum.parse(reqDTO.getSupportReturn()))
                .type(ProductType.parse(reqDTO.getType()))
                .brandId(reqDTO.getBrandId())
                .categoryId(reqDTO.getCategoryId())
                .supplierId(reqDTO.getSupplierId())
                .warehouseId(reqDTO.getWarehouseId())
                .deliveryRegion(reqDTO.getDeliveryRegion())
                .createdBy(0L)
                .createdFrom(Instant.ofEpochMilli(reqDTO.getCreatedFrom()))
                .createdTo(Instant.ofEpochMilli(reqDTO.getCreatedTo()))
                .pageIndex(reqDTO.getPageIndex())
                .pageSize(reqDTO.getPageSize())
                .build();
    }


    /**
     * 转换为创建命令
     */
    public CreateSpuCommand translateFromReqDTO(CreateSpuReqDTO reqDTO) {
        Long currentUserId = 10000L;

        List<ProductSku> productSkus = reqDTO.getSkus().stream().map(
                item -> ProductSku.builder()
                        .specIds(item.getSpecIds())
                        .skuCode(item.getSkuCode())
                        .imageIds(item.getImageIds())
                        .inventory(item.getInventory())
                        .safetyStock(item.getSafetyStock())
                        .price(item.getPrice())
                        .reduction(item.getReduction())
                        .promoteFee(item.getPromoteFee())
                        .serviceFee(item.getServiceFee())
                        .exchangePoint(item.getExchangePoint())
                        .netWorth(item.getNetWorth())
                        .barCode(item.getBarCode())
                        .createdBy(currentUserId)
                        .build()
        ).collect(Collectors.toList());

        return CreateSpuCommand.builder()
                .spuId(new SpuId())
                .name(reqDTO.getName())
                .spuCode(reqDTO.getSpuCode())
                .subTitle(reqDTO.getSubTitle())
                .imageId(reqDTO.getImageId())
                .albumImages(reqDTO.getAlbumImages())
                .skus(productSkus)
                .weight(reqDTO.getWeight())
                .model(reqDTO.getModel())
                .marketPrice(reqDTO.getMarketPrice())
                .price(reqDTO.getPrice())
                .inventory(reqDTO.getInventory())
                .safetyStock(reqDTO.getSafetyStock())
                .description(reqDTO.getDescription())
                .freightTemplateId(reqDTO.getFreightTemplateId())
                .type(ProductType.parse(reqDTO.getType()))
                .supportReturn(YesNoEnum.parse(reqDTO.getSupportReturn()))
                .recommended(YesNoEnum.parse(reqDTO.getRecommended()))
                .newProduct(YesNoEnum.parse(reqDTO.getNewProduct()))
                .brandId(reqDTO.getBrandId())
                .categoryId(reqDTO.getCategoryId())
                .warehouseId(reqDTO.getWarehouseId())
                .supplierId(reqDTO.getSupplierId())
                .h5Detail(reqDTO.getH5Detail())
                .deliveryRegion(reqDTO.getDeliveryRegion())
                .createdBy(currentUserId)
                .build();
    }
}
