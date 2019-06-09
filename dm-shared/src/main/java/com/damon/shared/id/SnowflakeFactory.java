package com.damon.shared.id;

/**
 * 雪花Id生成方法
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月17日 21:18
 */
public interface SnowflakeFactory extends BaseIdFactory<Long> {

    /**
     * 获取唯一ID，加前缀
     * @param clazz ID关联类
     * @return 生成的ID值
     */
    Long nextId(Class clazz);
}
