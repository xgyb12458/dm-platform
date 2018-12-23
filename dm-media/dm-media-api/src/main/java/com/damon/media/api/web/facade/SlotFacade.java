package com.damon.media.api.web.facade;

import com.damon.media.shared.constant.ApiConstants;
import com.damon.media.api.dto.req.slot.CreateSlotReqDTO;
import com.damon.media.api.dto.req.slot.QuerySlotReqDTO;
import com.damon.media.api.dto.req.slot.UpdateSlotReqDTO;
import com.damon.media.api.dto.resp.slot.SlotInfoRespDTO;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * slot facade
 * @author Damon S.
 */
@RequestMapping(value = "/ads" + ApiConstants.API_V1 + "/media/slots", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SlotFacade {

    @GetMapping
    ResponseWrapper<Pagination<SlotInfoRespDTO>> query(
            @RequestBody @Validated QuerySlotReqDTO querySlotReqDTO
    );


    @PostMapping
    ResponseWrapper<Long> create(
            @RequestBody @Validated CreateSlotReqDTO createSlotReqDTO
    );


    @PutMapping
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateSlotReqDTO updateSlotReqDTO
    );


    @PutMapping("/{slotId}/activation")
    ResponseWrapper<Boolean> activate(
            @PathVariable(value = "slotId") Long slotId
    );


    @PutMapping("/{slotId}/deactivation")
    ResponseWrapper<Boolean> deactivate(
            @PathVariable(value = "slotId") Long slotId
    );


    @PutMapping("/{slotId}/pass")
    ResponseWrapper<Boolean> pass(
            @PathVariable(value = "slotId") Long slotId
    );


    @PutMapping("/{slotId}/rejection")
    ResponseWrapper<Boolean> reject(
            @PathVariable(value = "slotId") Long slotId
    );


    @GetMapping("/{slotId}")
    ResponseWrapper<SlotInfoRespDTO> findById(
            @PathVariable(value = "slotId") Long slotId
    );
}
