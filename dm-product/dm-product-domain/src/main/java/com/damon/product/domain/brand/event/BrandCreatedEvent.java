package com.damon.product.domain.brand.event;

import com.damon.product.domain.brand.aggregate.BrandId;
import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;

/**
 * 新建品牌成功事件
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月24日 16:22
 */
@Value
@Builder
public class BrandCreatedEvent {
    @TargetAggregateIdentifier
    private final BrandId       brandId;
    /**品牌名称*/
    private final String        name;
    /**品牌编码*/
    private final String        code;
    /**品牌主页*/
    private final String        homepage;
    /**品牌LOGO*/
    private final String        logo;
    /**首字母*/
    private final String        firstLetter;
    /**排序*/
    private final Integer       sort;
    /**是否显示*/
    private final YesNoEnum     display;
    /**是否为品牌制造商*/
    private final YesNoEnum     factoryState;
    /**是否已删除*/
    private final YesNoEnum     removed;
    /**专区大图*/
    private final String        bigImage;
    /**品牌故事*/
    private final String        description;
    /**创建人*/
    private final Long          createdBy;
    /**创建时间*/
    private final Instant       createdAt;
}
