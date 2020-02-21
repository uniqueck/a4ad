package com.github.a4ad.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTest {

    @Test
    void testWithId() {
        Environment environment = Environment.withId(new Environment.EnvironmentId(20L));
        assertNotNull(environment);
        assertNotNull(environment.getId());
        assertTrue(environment.getId().isPresent());
        assertEquals(new Environment.EnvironmentId(20L), environment.getId().get());
    }

    @Test
    void testWithoutId() {
        Environment environment = Environment.withoutId();
        assertNotNull(environment);
        assertNotNull(environment.getId());
        assertFalse(environment.getId().isPresent());
    }

    @Test
    void testGetId() {
        Environment environment = Environment.withId(new Environment.EnvironmentId(20L));
        assertThat(environment.getId()).isNotNull().as("getId should return an Optional");
        assertThat(environment.getId().isPresent()).isTrue().as("environment is created with id, so optional should present");
        assertThat(environment.getId().get()).isEqualTo(new Environment.EnvironmentId(20L)).as("value of optional should equal to manual created id");
        assertThat(environment.getId().get().getValue()).isEqualTo(20L);
    }


}