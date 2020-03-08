package com.github.a4ad.domain;

public interface ValueObject<T> {

    boolean sameValueAs(T o);

}
