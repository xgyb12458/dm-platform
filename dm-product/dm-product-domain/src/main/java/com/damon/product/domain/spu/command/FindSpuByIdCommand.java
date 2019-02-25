package com.damon.product.domain.spu.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 获取指定SpuId的商品
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月25日 23:40
 */
@Value
@RequiredArgsConstructor
public class FindSpuByIdCommand {
    private final Long  spuId;
}
