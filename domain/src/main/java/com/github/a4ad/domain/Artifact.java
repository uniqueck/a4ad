package com.github.a4ad.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public class Artifact {

    private final ArtifactId id;

    public static Artifact withId(ArtifactId id) {
        return new Artifact(id);
    }

    public static Artifact withoutId() {
        return new Artifact(null);
    }

    @Value
    public static class ArtifactId {
        private Long value;
    }

}
