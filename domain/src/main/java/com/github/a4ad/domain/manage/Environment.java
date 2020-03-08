package com.github.a4ad.domain.manage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Environment {

    private final EnvironmentId id;

    public static Environment withId(EnvironmentId id) {
        return new Environment(id);
    }

    public static Environment withoutId() {
        return new Environment(null);
    }

    public Optional<EnvironmentId> getId() {
        return Optional.ofNullable(this.id);
    }


    @Value
    public static class EnvironmentId {
        Long value;
    }

}
