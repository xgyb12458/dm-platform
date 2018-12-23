package com.damon.media.api.web.facade;


import com.damon.media.api.dto.req.slot.layout.CreateLayoutReqDTO;
import com.damon.media.api.dto.req.slot.layout.QueryLayoutReqDTO;
import com.damon.media.api.dto.req.slot.layout.UpdateLayoutReqDTO;
import com.damon.media.api.dto.resp.slot.layout.LayoutInfoRespDTO;
import com.damon.media.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * layout facade
 * @author Damon S.
 */
@RequestMapping(value = "/ads" + ApiConstants.API_V1 + "/media/slot/layouts", produces = MediaType.APPLICATION_JSON_VALUE)
public interface LayoutFacade {

    @GetMapping
    ResponseWrapper<Pagination<LayoutInfoRespDTO>> query(
            @RequestBody @Validated QueryLayoutReqDTO queryLayoutReqDTO
    );


    @PostMapping
    ResponseWrapper<Long> create(
            @RequestBody @Validated CreateLayoutReqDTO createLayoutReqDTO
    );


    @PutMapping
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateLayoutReqDTO updateLayoutReqDTO
    );


    @PutMapping("/{layoutId}/activation")
    ResponseWrapper<Boolean> activate(
            @PathVariable(value = "layoutId") Long layoutId
    );


    @PutMapping("/{layoutId}/deactivation")
    ResponseWrapper<Boolean> deactivate(
            @PathVariable(value = "layoutId") Long layoutId
    );


    @GetMapping("/{layoutId}")
    ResponseWrapper<LayoutInfoRespDTO> findLayoutById(
            @PathVariable(value = "layoutId") Long layoutId
    );
}
