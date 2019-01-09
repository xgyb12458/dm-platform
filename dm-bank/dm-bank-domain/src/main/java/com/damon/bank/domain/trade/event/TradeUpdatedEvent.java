package com.damon.bank.domain.trade.event;

import com.damon.bank.domain.trade.aggregate.TradeId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.sql.Timestamp;

/**
 * 交易更新事件
 * @author Damon S.
 */
@Getter
@Builder
public class TradeUpdatedEvent {
    @TargetAggregateIdentifier
    private final TradeId tradeId;
    private final String name;
    private final String permsJson;
    private final Long updatedBy;
    private final Timestamp updatedAt;
}
