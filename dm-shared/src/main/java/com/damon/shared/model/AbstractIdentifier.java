package com.damon.shared.model;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/***
 * 值对象抽象类
 * @author Damon S.
 * @param <T> 值基础类型
 */
public abstract class AbstractIdentifier<T extends AbstractIdentifier<T, V>, V> implements ValueObject<T> {

    private final V identifier;
    private final int hashCode;

    protected AbstractIdentifier(V identifier) {
        Assert.notNull(identifier, "Identifier must not be null");
        this.identifier = identifier;
        this.hashCode = identifier.hashCode();
    }

    public V getValue() {
        return identifier;
    }

    @Override
    public boolean sameAs(T o) {
        return this == o
                || !ObjectUtils.isEmpty(o)
                && getClass() == o.getClass()
                && getValue() == o.getValue();
    }

    @Override
    public boolean equals(Object o) {
        return sameAs((T)o);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return identifier.toString();
    }
}
