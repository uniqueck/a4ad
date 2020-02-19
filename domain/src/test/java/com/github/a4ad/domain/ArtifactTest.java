package com.github.a4ad.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactTest {

    @Test
    void withId() {
        Artifact artifact = Artifact.withId(new Artifact.ArtifactId(20L));
        assertNotNull(artifact);
    }

    @Test
    void withoutId() {
        Artifact artifact = Artifact.withoutId();
        assertNotNull(artifact);
    }

}