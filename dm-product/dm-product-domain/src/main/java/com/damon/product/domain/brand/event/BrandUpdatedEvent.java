package com.damon.product.domain.brand.event;

import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;

/**
 * 品牌更新成功事件
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月24日 16:23
 */
@Value
@Builder
public class BrandUpdatedEvent {
    @TargetAggregateIdentifier
    private final Long          brandId;
    /**品牌名称*/
    private final String        name;
    /**品牌编码*/
    private final String        code;
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
    private final YesNoEnum     deleted;
    /**专区大图*/
    private final String        bigImage;
    /**品牌故事*/
    private final String        brandStory;
    /**更新人*/
    private final Long          updatedBy;
    /**更新时间*/
    private final Instant       updatedAt;
}
