package com.damon.product.domain.spu.command;

import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;

/**
 * 修改品牌命令
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 18:19
 */
@Value
@Builder
public class UpdateBrandCommand {
    private final Long          brandId;
    /**品牌名称*/
    private final String        name;
    /**品牌编码*/
    private final String        code;
    /**排序*/
    private final Integer       sort;
    /**是否显示*/
    private final YesNoEnum     display;
    /**是否为品牌制造商*/
    private final YesNoEnum     factoryState;
    /**首字母*/
    private final String        firstLetter;
    /**品牌LOGO*/
    private final String        logo;
    /**专区大图*/
    private final String        bigImage;
    /**品牌故事*/
    private final String        brandStory;
    /**创建人*/
    private final Long          updatedBy;
}
