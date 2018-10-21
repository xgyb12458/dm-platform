package com.damon.shared.model;

/**
 * @author Damon S.
 * @param <ID>
 */
public interface Identifiable<ID> {

    /***
     * 获取唯一标识
     * @return 唯一标识
     */
    ID identity();
}
