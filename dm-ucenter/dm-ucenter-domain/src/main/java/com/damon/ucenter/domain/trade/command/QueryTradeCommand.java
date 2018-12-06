package com.damon.ucenter.domain.trade.command;

import com.damon.shared.enums.SwitchState;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Damon S.
 */
@Getter
@Builder
public class QueryTradeCommand {
    private final String roleId;
    private final String code;
    private final String name;
    private final SwitchState state;
    private final Integer pageSize;
    private final Integer pageIndex;
}
