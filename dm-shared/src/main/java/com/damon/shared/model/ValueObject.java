package com.damon.shared.model;

import java.io.Serializable;

/**
 * @author Damon S.
 */
public interface ValueObject<T extends ValueObject<T>> extends Serializable {
}
