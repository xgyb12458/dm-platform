package com.damon.media.api.web.facade;

import com.damon.media.shared.constant.ApiConstants;
import com.damon.media.api.dto.req.slot.spec.CreateSpecReqDTO;
import com.damon.media.api.dto.req.slot.spec.QuerySpecReqDTO;
import com.damon.media.api.dto.req.slot.spec.UpdateSpecReqDTO;
import com.damon.media.api.dto.resp.slot.spec.SpecInfoRespDTO;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * specification facade
 * @author Damon S.
 */
@RequestMapping(value = "/ads" + ApiConstants.API_V1 + "/media/specs", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SpecFacade {

    @GetMapping
    ResponseWrapper<Pagination<SpecInfoRespDTO>> query(
            @RequestBody @Validated QuerySpecReqDTO querySpecReqDTO
    );


    @PostMapping
    ResponseWrapper<Long> create(
            @RequestBody @Validated CreateSpecReqDTO createSpecReqDTO
    );


    @PutMapping
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateSpecReqDTO updateSpecReqDTO
    );


    @PutMapping("/{specId}/activation")
    ResponseWrapper<Boolean> activate(
            @PathVariable(value = "specId") Long specId
    );


    @PutMapping("/{specId}/deactivation")
    ResponseWrapper<Boolean> deactivate(
            @PathVariable(value = "specId") Long specId
    );


    @GetMapping("/{specId}")
    ResponseWrapper<SpecInfoRespDTO> findById(
            @PathVariable(value = "specId") Long specId
    );
}
