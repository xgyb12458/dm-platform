package com.damon.product.domain.category.command;

import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;

/**
 * 查询品类信息
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 21:56
 */
@Value
@Builder
public class QueryCategoryCommand {

    private final String  name;
    /**
     * 是否显示在导航栏
     */
    private final YesNoEnum navState;
    /**
     * 显示状态
     */
    private final YesNoEnum showState;
    /**
     * 关键字
     */
    private final String keywords;
    /**
     * 上级分类的编号：0表示一级分类
     */
    private final Long  parentId;

    private final Long pageSize;

    private final Long pageIndex;
}
