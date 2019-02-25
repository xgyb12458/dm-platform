package com.damon.product.api.web.facade;

import com.damon.product.api.dto.req.spu.CreateSpuReqDTO;
import com.damon.product.api.dto.req.spu.QuerySpuReqDTO;
import com.damon.product.api.dto.req.spu.UpdateSpuReqDTO;
import com.damon.product.api.dto.resp.spu.CreateSpuRespDTO;
import com.damon.product.api.dto.resp.spu.SpuInfoRespDTO;
import com.damon.product.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/product/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface ProductFacade {


    @PostMapping("/spus")
    ResponseWrapper<CreateSpuRespDTO> create(
            @RequestBody @Validated CreateSpuReqDTO createSpuReqDTO
    );


    @GetMapping("/spus")
    ResponseWrapper<Pagination<SpuInfoRespDTO>> query(
            @RequestBody @Validated QuerySpuReqDTO querySpuReqDTO
    );


    @PutMapping("/spus")
    ResponseWrapper<Boolean> update(
            @RequestBody @Validated UpdateSpuReqDTO updateSpuReqDTO
    );


    @GetMapping("/spus/{spuId}")
    ResponseWrapper<SpuInfoRespDTO> find(
            @PathVariable(name = "spuId") Long spuId
    );


    @DeleteMapping("/spus/{spuId}")
    ResponseWrapper<Boolean> remove(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/recovery")
    ResponseWrapper<Boolean> recover(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/changeNewProduct")
    ResponseWrapper<Boolean> changeNewProductState(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/changeRecommend")
    ResponseWrapper<Boolean> changeRecommendState(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/changeSoldOut")
    ResponseWrapper<Boolean> changeSoldOutState(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/changeCanReturn")
    ResponseWrapper<Boolean> changeCanReturnState(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/verification/reset")
    ResponseWrapper<Boolean> resetVerifyState(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/verification/commit")
    ResponseWrapper<Boolean> commitVerification(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/verification/approval")
    ResponseWrapper<Boolean> approveSpu(
            @PathVariable(name = "spuId") Long spuId
    );


    @PutMapping("/spus/{spuId}/verification/rejection")
    ResponseWrapper<Boolean> rejectSpu(
            @PathVariable(name = "spuId") Long spuId
    );


    @PostMapping("/spus/batchRemove")
    ResponseWrapper<Boolean> batchRemove(
            List<Long> spuIds
    );


    @PostMapping("/spus/batchChangeNewProduct")
    ResponseWrapper<Boolean> batchChangeNewProductState(
            List<Long> spuIds
    );


    @PostMapping("/spus/batchChangeRecommend")
    ResponseWrapper<Boolean> batchChangeRecommendState(
            List<Long> spuIds
    );


    @PostMapping("/spus/batchChangeSoldOut")
    ResponseWrapper<Boolean> batchChangeSoldOutState(
            List<Long> spuIds
    );


    @PostMapping("/spus/batchChangeCanReturn")
    ResponseWrapper<Boolean> batchChangeCanReturnState(
            List<Long> spuIds
    );
}
