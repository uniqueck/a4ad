package com.github.a4ad.domain;

public interface Entity<T> {

    boolean sameIdentityAs(T other);

}
