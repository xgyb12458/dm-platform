package com.damon.product.domain.brand.command;

import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;

/**
 * 查询品牌命令
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 18:19
 */
@Value
@Builder
public class QueryBrandCommand {
    /**品牌标识*/
    private final Long          brandId;
    /**品牌名称*/
    private final String        name;
    /**品牌编码*/
    private final String        code;
    /**是否显示*/
    private final YesNoEnum     display;
    /**是否为品牌制造商*/
    private final YesNoEnum     factoryState;
    /**首字母*/
    private final String        firstLetter;
    /**创建人*/
    private final Long          createdBy;
    /**创建时间-从*/
    private final String        createdFrom;
    /**创建时间-到*/
    private final String        createdTo;
}
