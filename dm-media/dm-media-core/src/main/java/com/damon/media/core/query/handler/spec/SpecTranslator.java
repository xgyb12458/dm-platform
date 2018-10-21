package com.damon.media.core.query.handler.spec;

import com.damon.media.api.dto.resp.slot.spec.SpecInfoRespDTO;
import com.damon.media.domain.slot.spec.aggregate.SpecExtension;
import com.damon.media.domain.slot.spec.entity.SlotSpecEntry;
import com.damon.shared.common.Constants;
import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.enums.YesNoEnum;
import com.damon.shared.exception.BusinessException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.QueryResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Damon S.
 */
@Component
public class SpecTranslator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecTranslator.class);

    private final ObjectMapper mapper = new ObjectMapper();

    public SpecInfoRespDTO translateToRespDTO(SlotSpecEntry specEntry) {
        SpecInfoRespDTO specInfoRespDTO = new SpecInfoRespDTO();
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Long.class);
            SpecExtension specExt = mapper.readValue(specEntry.getSpecExt(), SpecExtension.class);

            BeanUtils.copyProperties(specEntry, specInfoRespDTO);
            specInfoRespDTO.setLayoutIds(mapper.readValue(specEntry.getLayoutIds(), javaType));
            specInfoRespDTO.setEditable(YesNoEnum.YES.getValue());
            specInfoRespDTO.setFrameCount(Optional.ofNullable(specExt.getFrameCount()).orElse(Constants.INT_ZERO));
            specInfoRespDTO.setLookAndFeel(Optional.ofNullable(specExt.getLookAndFeel()).orElse(Constants.EMPTY));
        } catch (IOException e) {
            LOGGER.error("=========>>查询信息流布局样式信息失败：" + e.toString());
            throw new BusinessException(ResponseCodeEnum.INTERNAL_ERROR, "信息流规格布局样式信息获取失败");
        }
        return specInfoRespDTO;
    }


    public List<SpecInfoRespDTO> translateToRespDTOs(QueryResults<SlotSpecEntry> specEntries) {
        List<SpecInfoRespDTO> specInfoRespDTOs = new ArrayList<>(specEntries.getResults().size());
        specEntries.getResults().forEach(
                entry -> specInfoRespDTOs.add(translateToRespDTO(entry))
        );
        return specInfoRespDTOs;
    }
}
