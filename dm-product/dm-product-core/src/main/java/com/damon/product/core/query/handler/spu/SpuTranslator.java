package com.damon.product.core.query.handler.spu;

import com.damon.product.api.dto.resp.spu.SpuInfoRespDTO;
import com.damon.product.domain.spu.entity.SpuEntry;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * SKU实用工具
 * @author Damon S.
 */
@Component
public final class SpuTranslator {

    /**
     * 转换SPU对象
     */
    public static SpuInfoRespDTO translateToRespDTO(SpuEntry spuEntry) {
        SpuInfoRespDTO spuInfoRespDTO = new SpuInfoRespDTO();
        BeanUtils.copyProperties(spuEntry, spuInfoRespDTO);
        return spuInfoRespDTO;
    }

    /**
     * 转换SPU对象列表
     */
    public static List<SpuInfoRespDTO> translateToRespDTOs(QueryResults<SpuEntry> spuEntries) {
        List<SpuInfoRespDTO> spuInfoRespDTOs = new ArrayList<>(spuEntries.getResults().size());
        spuEntries.getResults().forEach(
                entry -> spuInfoRespDTOs.add(translateToRespDTO(entry))
        );
        return spuInfoRespDTOs;
    }
}
