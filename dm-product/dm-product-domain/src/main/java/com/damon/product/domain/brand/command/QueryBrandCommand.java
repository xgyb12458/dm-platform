package com.damon.product.domain.brand.command;

import com.damon.shared.enums.YesNoEnum;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

/**
 * 查询品牌命令
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月23日 18:19
 */
@Value
@Builder
public class QueryBrandCommand {
    /**品牌名称*/
    private final String        name;
    /**品牌编码*/
    private final String        code;
    /**是否为品牌制造商*/
    private final YesNoEnum     factoryState;
    /**是否已删除*/
    private final YesNoEnum     removed;
    /**首字母*/
    private final String        firstLetter;
    /**创建人*/
    private final Long          createdBy;
    /**创建时间-从*/
    private final Instant       createdFrom;
    /**创建时间-到*/
    private final Instant       createdTo;
    /**分页-每页数量*/
    private final Long          pageSize;
    /**分页-页码*/
    private final Long          pageIndex;
}
