package com.damon.product.domain.spu.event;

import com.damon.product.domain.spu.aggregate.SpuId;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/***
 * 交易创建完成事件
 * @author Damon S.
 */
@Getter
@Builder
public class ProductCreatedEvent {
    @TargetAggregateIdentifier
    private final SpuId spuId;
}
