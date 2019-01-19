package com.damon.product.core.query.handler.trade;

import com.damon.product.api.dto.resp.trade.ConfirmOrderRespDTO;
import com.damon.product.domain.spu.entity.SpuEntry;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damon S.
 */
@Service
public final class SpuTranslator {

    public ConfirmOrderRespDTO translateToRespDTO(SpuEntry spuEntry) {
        ConfirmOrderRespDTO confirmOrderRespDTO = ConfirmOrderRespDTO.builder().build();
        BeanUtils.copyProperties(spuEntry, confirmOrderRespDTO);
        return confirmOrderRespDTO;
    }

    public List<ConfirmOrderRespDTO> translateToRespDTOs(QueryResults<SpuEntry> spuEntries) {
        List<ConfirmOrderRespDTO> confirmOrderRespDTOS = new ArrayList<>(spuEntries.getResults().size());
        spuEntries.getResults().forEach(
                entry -> confirmOrderRespDTOS.add(translateToRespDTO(entry))
        );
        return confirmOrderRespDTOS;
    }
}
