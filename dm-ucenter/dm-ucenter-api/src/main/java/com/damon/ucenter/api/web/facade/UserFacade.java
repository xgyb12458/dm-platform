package com.damon.ucenter.api.web.facade;

import com.damon.ucenter.api.dto.req.user.CreateUserReqDTO;
import com.damon.ucenter.api.dto.req.user.QueryUserReqDTO;
import com.damon.ucenter.api.dto.req.user.UpdateUserReqDTO;
import com.damon.ucenter.api.dto.resp.user.UserInfoRespDTO;
import com.damon.ucenter.api.dto.resp.user.CreateUserRespDTO;
import com.damon.ucenter.shared.constant.ApiConstants;
import com.damon.shared.common.Pagination;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户中心管理接口
 * @author Damon S.
 */
@RequestMapping(value = "/ucenter/" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserFacade {


    @GetMapping("/cart/items")
    ResponseWrapper<Pagination<CreateUserRespDTO>> list(
            @RequestBody @Validated QueryUserReqDTO queryUserReqDTO
    );


    @PostMapping("/cart/items")
    ResponseWrapper<UserInfoRespDTO> add(
            @RequestBody @Validated CreateUserReqDTO createUserReqDTO
    );


    @DeleteMapping("/cart/items")
    ResponseWrapper<Boolean> remove(
            @RequestBody @Validated UpdateUserReqDTO updateUserReqDTO
    );
}
