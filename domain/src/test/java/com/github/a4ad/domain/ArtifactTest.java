package com.github.a4ad.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactTest {

    @Test
    void withId() {
        Artifact artifact = Artifact.withId(new Artifact.ArtifactId(20L));
        assertNotNull(artifact);
        assertNotNull(artifact.getId());
        assertTrue(artifact.getId().isPresent());
        assertEquals(new Artifact.ArtifactId(20L), artifact.getId().get());
    }

    @Test
    void withoutId() {
        Artifact artifact = Artifact.withoutId();
        assertNotNull(artifact);
        assertNotNull( artifact.getId());
        assertFalse(artifact.getId().isPresent());
    }

    @Test
    void testGetId() {
        Artifact artifact = Artifact.withId(new Artifact.ArtifactId(20L));
        assertTrue(artifact.getId().isPresent());
        assertEquals(new Artifact.ArtifactId(20L), artifact.getId().get());
        assertEquals(20L, artifact.getId().get().getValue());
    }


}