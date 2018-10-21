package com.damon.oauth.domain.user.command;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class UserLoginCommand {
    private final String userName;
    private final String password;
    private final String captcha;
}
