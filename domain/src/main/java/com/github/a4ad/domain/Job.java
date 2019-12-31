package com.github.a4ad.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Job {


    private final Jobid id;

    public static Job withId(Jobid id) {
        return new Job(id);
    }

    public static Job withoutId() {
        return new Job(null);
    }

    public Optional<Jobid> getId() {
        return Optional.ofNullable(this.id);
    }


    @Value
    public static class Jobid {
        private Long value;
    }

}
