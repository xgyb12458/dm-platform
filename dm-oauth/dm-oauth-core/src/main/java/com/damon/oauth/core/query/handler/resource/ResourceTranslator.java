package com.damon.oauth.core.query.handler.resource;

import com.damon.oauth.domain.resource.command.CreateResourceCommand;
import com.damon.oauth.domain.resource.command.UpdateResourceCommand;
import com.damon.oauth.manager.dto.req.resource.CreateResourceReqDTO;
import com.damon.oauth.manager.dto.req.resource.UpdateResourceReqDTO;
import com.damon.shared.common.Constants;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

/**
 * 资源域辅助工具类
 * @author Damon S.
 */
@Service
public class ResourceTranslator {

    /**
     * 实例化一个新建资源命令对象，
     * 1. 进行参数的合法性验证
     * 2. 获取并设置创建用户
     * @param createResourceReqDTO 入参
     * @return 新建资源命令对象
     */
    public CreateResourceCommand buildCreateCommand(CreateResourceReqDTO createResourceReqDTO) {
        Optional.ofNullable(createResourceReqDTO).orElseThrow(InvalidParameterException::new);
        return CreateResourceCommand.builder()
                .code(Optional.ofNullable(createResourceReqDTO.getCode()).orElseThrow(InvalidParameterException::new))
                .name(Optional.ofNullable(createResourceReqDTO.getName()).orElseThrow(InvalidParameterException::new))
                .path(Optional.ofNullable(createResourceReqDTO.getPath()).orElse(Constants.STR_SHARP))
                .sort(Optional.ofNullable(createResourceReqDTO.getSort()).orElse(Constants.INT_ZERO))
                .parentId(Optional.ofNullable(createResourceReqDTO.getParentId()).orElse(Constants.LONG_ZERO))
                .platform(Optional.ofNullable(createResourceReqDTO.getPlatform()).orElseThrow(InvalidParameterException::new))
                .createdBy(0L)
                .tenantId(null)
                .build();
    }


    public UpdateResourceCommand buildUpdateCommand(UpdateResourceReqDTO updateResourceReqDTO) {
        return UpdateResourceCommand.builder()
                .resourceId(Optional.ofNullable(updateResourceReqDTO.getResourceId()).orElseThrow(InvalidParameterException::new))
                .name(Optional.ofNullable(updateResourceReqDTO.getName()).orElseThrow(InvalidParameterException::new))
                .path(Optional.ofNullable(updateResourceReqDTO.getPath()).orElseThrow(InvalidParameterException::new))
                .sort(Optional.ofNullable(updateResourceReqDTO.getSort()).orElseThrow(InvalidParameterException::new))
                .platform(Optional.ofNullable(updateResourceReqDTO.getPlatform()).orElseThrow(InvalidParameterException::new))
                .updatedBy(0L)
                .build();
    }
}
