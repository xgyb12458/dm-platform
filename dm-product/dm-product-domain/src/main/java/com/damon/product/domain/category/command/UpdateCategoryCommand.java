package com.damon.product.domain.category.command;

import com.damon.product.domain.category.aggregate.CategoryId;
import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 更新品类命令
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 21:54
 */
@Value
@Builder
public class UpdateCategoryCommand {
    @TargetAggregateIdentifier
    private final CategoryId categoryId;
    /**
     * 品类名称
     */
    private final String  name;
    /**
     * '品类级别：0->1级；1->2级'
     */
    private final String level;
    /**
     * 图标
     */
    private final String icon;
    /**
     * '商品数量'
     */
    private final Integer spuCount;
    /**
     * '数量单位'
     */
    private final String spuUnit;
    /**
     * 是否显示在导航栏
     */
    private final YesNoEnum navState;
    /**
     * 显示状态
     */
    private final YesNoEnum showState;
    /**
     * 排序
     */
    private final Integer sort;
    /**
     * 关键字
     */
    private final String keywords;
    /**
     * 上级分类的编号：0表示一级分类
     */
    private final Long  parentId;
    private final String description;
    private final Long  updatedBy;
}
