package com.damon.product.shared.utils;

import com.damon.shared.common.Constants;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 集合工具类，与业务相关
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年02月27日 19:50
 */
public class CollectionUtil {

    private final static Integer NUM_ONE = 1;

    /**
     * 将Long集合转换为逗号分隔的字符串进行存储
     * @param collection Long集合
     * @return 逗号分隔的字符串或者为空
     */
    public static String collection2Plain(Collection<Long> collection) {
        StringBuilder builder = new StringBuilder();

        if (!CollectionUtils.isEmpty(collection)) {
            collection.forEach(imageId ->
                    builder.append(Constants.STR_COMMA).append(imageId)
            );
        }
        if (builder.length() <= NUM_ONE) {
            return Constants.STR_EMPTY;
        }
        return builder.substring(NUM_ONE);
    }
}
