package com.damon.product.domain.brand.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 通过Id查找品牌
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 22:43
 */
@Value
@RequiredArgsConstructor
public class FindBrandByIdCommand {
    private final Long brandId;
}
