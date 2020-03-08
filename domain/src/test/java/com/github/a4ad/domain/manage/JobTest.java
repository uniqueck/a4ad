package com.github.a4ad.domain.manage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void withId() {
        Job job = Job.withId(new JobId(20L));
        assertNotNull(job);
        assertNotNull(job.getId());
        assertTrue(job.getId().isPresent());
        assertEquals(new JobId(20L), job.getId().get());
    }

    @Test
    void withoutId() {
        Job job = Job.withoutId();
        assertNotNull(job);
        assertNotNull(job.getId());
        assertFalse(job.getId().isPresent());
    }

    @Test
    void getId() {
        Job job = Job.withId(new JobId(20L));
        assertThat(job.getId()).isNotNull().as("optional should be not null");
        assertThat(job.getId().isPresent()).isTrue().as("value of optional should be present");
        assertThat(job.getId().get().getValue()).isEqualTo(20L);


    }

}