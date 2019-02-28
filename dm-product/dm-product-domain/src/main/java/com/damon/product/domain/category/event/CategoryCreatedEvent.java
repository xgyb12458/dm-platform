package com.damon.product.domain.category.event;

import com.damon.product.domain.category.aggregate.CategoryId;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.sql.Timestamp;

/**
 * 商品品类创建事件
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月28日 10:15
 */
@Value
@Builder
public class CategoryCreatedEvent {
    @TargetAggregateIdentifier
    private final CategoryId categoryId;
    /**
     * 品类名称
     */
    private final String  name;
    /**
     * '品类级别：0->1级；1->2级'
     */
    private final Integer level;
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
    private final String navState;
    /**
     * 显示状态
     */
    private final String showState;
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
    private final Long      parentId;
    private final String    description;
    private final Long      createdBy;
    private final Timestamp createdAt;
}
