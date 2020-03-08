package com.github.a4ad.domain.manage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Optional;

@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public class Artifact {

    private final ArtifactId id;

    public static Artifact withId(ArtifactId id) {
        return new Artifact(id);
    }

    public static Artifact withoutId() {
        return new Artifact(null);
    }

    public Optional<ArtifactId> getId() {return Optional.ofNullable(id);}

    @Value
    public static class ArtifactId {
        private Long value;
    }

}
