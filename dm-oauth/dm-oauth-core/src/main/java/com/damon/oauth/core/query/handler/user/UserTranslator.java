package com.damon.oauth.core.query.handler.user;

import com.damon.oauth.api.dto.resp.user.UserInfoRespDTO;
import com.damon.oauth.domain.user.entity.UserEntry;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damon S.
 */
@Service
public final class UserTranslator {

    public UserInfoRespDTO translateToRespDTO(UserEntry appEntry) {
        UserInfoRespDTO userInfoRespDTO = new UserInfoRespDTO();
        BeanUtils.copyProperties(appEntry, userInfoRespDTO);
        return userInfoRespDTO;
    }

    public List<UserInfoRespDTO> translateToRespDTOs(QueryResults<UserEntry> appEntries) {
        List<UserInfoRespDTO> userInfoRespDTOs = new ArrayList<>(appEntries.getResults().size());
        appEntries.getResults().forEach(
                entry -> userInfoRespDTOs.add(translateToRespDTO(entry))
        );
        return userInfoRespDTOs;
    }
}
