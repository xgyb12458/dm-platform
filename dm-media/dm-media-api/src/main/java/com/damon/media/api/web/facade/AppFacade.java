package com.damon.media.api.web.facade;

import com.damon.media.api.dto.req.app.CreateAppReqDTO;
import com.damon.media.api.dto.req.app.QueryAppReqDTO;
import com.damon.media.api.dto.req.app.UpdateAppReqDTO;
import com.damon.media.api.dto.resp.app.AppInfoRespDTO;
import com.damon.media.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * app facade
 * @author Damon S.
 */
@RequestMapping(value = "/ads" + ApiConstants.API_V1 + "/media/apps", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AppFacade {

    @GetMapping
    ResponseWrapper<Pagination<AppInfoRespDTO>> query(
            @RequestBody @Validated QueryAppReqDTO queryAppReqDTO
    );


    @PostMapping
    ResponseWrapper<Long> create(
            @RequestBody @Validated CreateAppReqDTO createAppReqDTO
    );


    @GetMapping("/{appId}")
    ResponseWrapper<AppInfoRespDTO> findById(
            @PathVariable("appId") Long appId
    );


    @PutMapping
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateAppReqDTO updateAppReqDTO
    );


    @PutMapping("/{appId}/activation")
    ResponseWrapper<Boolean> activate(
            @PathVariable(value = "appId") Long appId
    );


    @PutMapping("/{appId}/deactivation")
    ResponseWrapper<Boolean> deactivate(
            @PathVariable(value = "appId") Long appId
    );


    @PutMapping("/{appId}/pass")
    ResponseWrapper<Boolean> pass(
            @PathVariable(value = "appId") Long appId
    );


    @PutMapping("/{appId}/rejection")
    ResponseWrapper<Boolean> reject(
            @PathVariable(value = "appId") Long appId
    );
}
