package com.damon.oauth.domain.user.command;

import com.damon.oauth.domain.user.aggregate.UserId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 更改密码
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年03月26日 09:04
 */
@Getter
@Builder
public class ChangePasswordCommand {
    @TargetAggregateIdentifier
    private final UserId        userId;
    private final String        password;
    private final String        newPassword;
}
