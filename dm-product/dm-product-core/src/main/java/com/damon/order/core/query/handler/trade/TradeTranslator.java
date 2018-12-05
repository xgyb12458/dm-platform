package com.damon.order.core.query.handler.trade;

import com.damon.order.api.dto.resp.trade.ConfirmOrderRespDTO;
import com.damon.order.domain.trade.entity.TradeEntry;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damon S.
 */
@Service
public final class TradeTranslator {

    public ConfirmOrderRespDTO translateToRespDTO(TradeEntry appEntry) {
        ConfirmOrderRespDTO confirmOrderRespDTO = ConfirmOrderRespDTO.builder().build();
        BeanUtils.copyProperties(appEntry, confirmOrderRespDTO);
        return confirmOrderRespDTO;
    }

    public List<ConfirmOrderRespDTO> translateToRespDTOs(QueryResults<TradeEntry> appEntries) {
        List<ConfirmOrderRespDTO> confirmOrderRespDTOS = new ArrayList<>(appEntries.getResults().size());
        appEntries.getResults().forEach(
                entry -> confirmOrderRespDTOS.add(translateToRespDTO(entry))
        );
        return confirmOrderRespDTOS;
    }
}
