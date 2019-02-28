package com.damon.product.domain.category.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 通过Id查找品类
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 22:43
 */
@Value
@RequiredArgsConstructor
public class FindCategoryByIdCommand {
    private final Long categoryId;
}
