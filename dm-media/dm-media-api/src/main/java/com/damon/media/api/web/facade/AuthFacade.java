package com.damon.media.api.web.facade;

import com.damon.media.api.dto.req.NormalLoginReqDTO;
import com.damon.media.api.dto.req.PhoneLoginReqDTO;
import com.damon.media.api.dto.resp.LoginRespDTO;
import com.damon.media.shared.constant.ApiConstants;
import com.damon.shared.wrapper.ResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * auth facade
 * @author Damon S.
 */
@RequestMapping(value = "/ads" + ApiConstants.API_V1 + "/media/oauth", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuthFacade {

    @PostMapping("/login")
    ResponseWrapper<LoginRespDTO> signInNormally(
            @RequestBody @Validated NormalLoginReqDTO normalLoginReqDTO
    );


    @PostMapping("/login2")
    ResponseWrapper<LoginRespDTO> signInByCaptcha(
            @RequestBody @Validated PhoneLoginReqDTO phoneLoginReqDTO
    );


    @GetMapping("/logout")
    ResponseWrapper<Boolean> logout();

}
