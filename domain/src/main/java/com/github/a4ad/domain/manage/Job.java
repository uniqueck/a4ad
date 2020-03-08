package com.github.a4ad.domain.manage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Job {


    private final JobId id;

    public static Job withId(JobId id) {
        return new Job(id);
    }

    public static Job withoutId() {
        return new Job(null);
    }

    public Optional<JobId> getId() {
        return Optional.ofNullable(this.id);
    }



}
