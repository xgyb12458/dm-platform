package com.damon.order.domain.trade.event;

import com.damon.order.domain.trade.aggregate.TradeId;
import com.damon.order.shared.enums.OrderState;
import com.damon.order.shared.enums.OrderType;
import com.damon.shared.tenant.TenantId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;


/***
 * 交易创建完成事件
 * @author Damon S.
 */
@Getter
@Builder
public class TradeCreatedEvent {
    @TargetAggregateIdentifier
    private final TradeId tradeId;
    private final String userName;
    private final String password;
    private final String salt;
    private final OrderType type;
    private final OrderState state;
    private final TenantId tenantId;
    private final Long createdBy;
    private final LocalDateTime createdAt;

}
