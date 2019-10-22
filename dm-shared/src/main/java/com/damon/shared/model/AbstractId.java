package com.damon.shared.model;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/***
 * 值对象抽象类
 * @author Damon S.
 * @param <T> 值基础类型
 */
public abstract class AbstractId<T extends AbstractId<T, V>, V> implements ValueObject {

    private final V identifier;
    private final int hashCode;

    protected AbstractId(V identifier) {
        Assert.notNull(identifier, "Identifier must not be null");
        this.identifier = identifier;
        this.hashCode = identifier.hashCode();
    }

    public V getValue() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        AbstractId id = null;
        if (o instanceof AbstractId) {
            id = (AbstractId) o;
        }
        return this == id
                || !ObjectUtils.isEmpty(id)
                && getClass() == id.getClass()
                && getValue() == id.getValue();
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
