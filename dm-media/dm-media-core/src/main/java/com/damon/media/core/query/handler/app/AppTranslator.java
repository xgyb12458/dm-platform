package com.damon.media.core.query.handler.app;

import com.damon.media.api.dto.resp.app.AppInfoRespDTO;
import com.damon.media.domain.app.entity.AppEntry;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damon S.
 */
@Service
public final class AppTranslator {

    public AppInfoRespDTO translateToRespDTO(AppEntry appEntry) {
        AppInfoRespDTO appInfoRespDTO = new AppInfoRespDTO();
        BeanUtils.copyProperties(appEntry, appInfoRespDTO);
        return appInfoRespDTO;
    }

    public List<AppInfoRespDTO> translateToRespDTOs(QueryResults<AppEntry> appEntries) {
        List<AppInfoRespDTO> appInfoRespDTOs = new ArrayList<>(appEntries.getResults().size());
        appEntries.getResults().forEach(
                entry -> appInfoRespDTOs.add(translateToRespDTO(entry))
        );
        return appInfoRespDTOs;
    }
}
