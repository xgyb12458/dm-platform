package com.damon.product.domain.spu.aggregate;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * SPU主商品
 * @author Damon S.
 */
@Slf4j
@Getter
@Setter(value = AccessLevel.PRIVATE)
@ToString
@Aggregate
@NoArgsConstructor
public class SpuAggregate {
}
