package com.damon.shared.id;

/**
 * 生成唯一Id的接口
 * @author Damon S.
 * @version v1.0.1
 * @date 2019年05月17日 20:50
 */
public interface BaseIdFactory<T> {

    /**
     * 获取唯一ID
     * @return 生成的ID值
     */
    T nextId();

    /**
     * 获取唯一ID，加前缀
     * @param prefix ID前缀
     * @return 生成的ID值
     */
    T nextId(String prefix);
}
