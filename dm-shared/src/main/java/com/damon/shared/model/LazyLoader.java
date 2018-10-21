package com.damon.shared.model;

import java.util.function.Supplier;

/**
 * 属性懒加载接口
 * @author Damon S.
 * @param <T>
 */
@FunctionalInterface
public interface LazyLoader<T> extends Supplier<T> {
}
