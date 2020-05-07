package com.github.a4ad.domain.manage;

import com.github.a4ad.domain.manage.server.TargetServer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Environment {

    private final EnvironmentId id;
    private final List<TargetServer> servers;

    public static Environment withId(EnvironmentId id, List<TargetServer> servers) {
        return new Environment(id, new ArrayList<>(servers));
    }

    public static Environment withoutId(List<TargetServer> servers) {
        return new Environment(null, new ArrayList<>(servers));
    }

    public Optional<EnvironmentId> getId() {
        return Optional.ofNullable(this.id);
    }


    @Value
    public static class EnvironmentId {
        Long value;
    }

}
