package com.damon.media.core.query.handler.layout;

import com.damon.media.api.dto.resp.slot.layout.LayoutInfoRespDTO;
import com.damon.media.domain.slot.layout.entity.FeedLayoutEntry;
import com.damon.media.shared.enums.LayoutType;
import com.damon.shared.enums.YesNoEnum;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 与查询信息流布局样式相关的功能
 * @author Damon S.
 */
@Service
public class LayoutTranslator {

    public LayoutInfoRespDTO translateToRespDTO(FeedLayoutEntry layoutEntry) {
        LayoutInfoRespDTO layoutInfoRespDTO = new LayoutInfoRespDTO();
        BeanUtils.copyProperties(layoutEntry, layoutInfoRespDTO);
        layoutInfoRespDTO.setImageCount(LayoutType.valueOf(layoutEntry.getLayoutType()).getImageCount());
        // TODO:
        layoutInfoRespDTO.setEditable(YesNoEnum.YES.getValue());
        return layoutInfoRespDTO;
    }


    public List<LayoutInfoRespDTO> translateToRespDTOs(QueryResults<FeedLayoutEntry> layoutEntries) {
        List<LayoutInfoRespDTO> layoutInfoRespDTOs = new ArrayList<>(layoutEntries.getResults().size());
        layoutEntries.getResults().forEach(
                entry -> layoutInfoRespDTOs.add(translateToRespDTO(entry))
        );
        return layoutInfoRespDTOs;
    }
}
