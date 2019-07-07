package com.damon.oauth.manager.web.facade;

import com.damon.oauth.shared.constant.ApiConstants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 用户权限管理
 * @author Damon S.
 */
@RequestMapping(value = "/oauth" + ApiConstants.API_V1, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface AuthorityFacade {

}
