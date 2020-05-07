package com.github.a4ad.domain.manage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTest {

    @Test
    void testWithId() {
        Environment environment = Environment.withId(new Environment.EnvironmentId(20L), new ArrayList<>());
        assertNotNull(environment);
        assertNotNull(environment.getId());
        assertTrue(environment.getId().isPresent());
        assertEquals(new Environment.EnvironmentId(20L), environment.getId().get());
    }

    @Test
    void testWithoutId() {
        Environment environment = Environment.withoutId(new ArrayList<>());
        assertNotNull(environment);
        assertNotNull(environment.getId());
        assertFalse(environment.getId().isPresent());
    }

    @Test
    void testGetId() {
        Environment environment = Environment.withId(new Environment.EnvironmentId(20L), new ArrayList<>());
        assertThat(environment.getId()).isNotNull().as("getId should return an Optional");
        assertThat(environment.getId().isPresent()).isTrue().as("environment is created with id, so optional should present");
        assertThat(environment.getId().get()).isEqualTo(new Environment.EnvironmentId(20L)).as("value of optional should equal to manual created id");
        assertThat(environment.getId().get().getValue()).isEqualTo(20L);
    }


}