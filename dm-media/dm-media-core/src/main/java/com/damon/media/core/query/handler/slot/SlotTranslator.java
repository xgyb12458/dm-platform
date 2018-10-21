package com.damon.media.core.query.handler.slot;

import com.damon.media.api.dto.resp.slot.SlotInfoRespDTO;
import com.damon.media.domain.slot.entity.SlotEntry;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.enums.YesNoEnum;
import com.damon.shared.exception.BusinessException;
import com.querydsl.core.QueryResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Damon S.
 */
@Service
public class SlotTranslator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlotTranslator.class);

    private final ObjectMapper mapper;
    private final JavaType javaType;

    public SlotTranslator() {
        this.mapper = new ObjectMapper();
        this.javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Long.class);
    }

    public SlotInfoRespDTO translateToRespDTO(SlotEntry slotEntry) {
        SlotInfoRespDTO slotInfoRespDTO = new SlotInfoRespDTO();
        BeanUtils.copyProperties(slotEntry, slotInfoRespDTO);
        slotInfoRespDTO.setEditable(YesNoEnum.YES.getValue());
        try {
            slotInfoRespDTO.setAppIds(mapper.readValue(slotEntry.getAppIds(), javaType));
        } catch (IOException e) {
            LOGGER.error("=========>>资源位关联App信息解析失败：" + e.toString());
            throw new BusinessException(ResponseCodeEnum.INTERNAL_ERROR, "资源位关联App信息解析失败");
        }
        return slotInfoRespDTO;
    }


    public List<SlotInfoRespDTO> translateToRespDTOs(QueryResults<SlotEntry> slotEntries) {
        List<SlotInfoRespDTO> slotInfoRespDTOs = new ArrayList<>(slotEntries.getResults().size());
        slotEntries.getResults().forEach(
                entry -> slotInfoRespDTOs.add(translateToRespDTO(entry))
        );
        return slotInfoRespDTOs;
    }
}
