package com.damon.shared.model;

import java.io.Serializable;

/**
 * @author Damon S.
 */
public interface ValueObject<T extends ValueObject<T>> extends Serializable {
    /***
     * 对比两个值对象
     * @param o 比较对象
     * @return 是否相等
     */
    boolean sameAs(T o);
}
