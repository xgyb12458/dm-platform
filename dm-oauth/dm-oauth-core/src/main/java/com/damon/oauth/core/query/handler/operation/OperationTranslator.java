package com.damon.oauth.core.query.handler.operation;

import com.damon.oauth.api.dto.resp.user.UserInfoRespDTO;
import com.damon.oauth.domain.user.entity.UserEntry;
import com.damon.oauth.manager.dto.req.operation.CreateOperationReqDTO;
import com.querydsl.core.QueryResults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作域复制类
 * @author Damon
 */
@Service
public class OperationTranslator {

    public boolean checkOperationParameters(CreateOperationReqDTO createOperationReqDTO) {

        return false;
    }

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
