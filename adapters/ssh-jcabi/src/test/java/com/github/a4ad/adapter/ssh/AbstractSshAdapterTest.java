package com.github.a4ad.adapter.ssh;

import com.github.a4ad.port.out.ssh.Authorization;
import com.github.a4ad.port.out.ssh.Destination;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractSshAdapterTest {

    @Container
    private GenericContainer<?> container = new GenericContainer<>("arvindr226/alpine-ssh");

    @BeforeEach
    void beforeEach() {
        assertTrue(container.isRunning());
    }

    protected String containerIpAddress() {
        return container.getContainerIpAddress();
    }

    protected int containerPort() {
        return container.getMappedPort(22);
    }

    protected org.testcontainers.containers.Container.ExecResult execInContainer(String... command) throws IOException, InterruptedException {
        return container.execInContainer(command);
    }

    protected Destination createDestination() {
        return Destination.of(containerIpAddress(), containerPort());
    }

    protected Authorization createAuthorization() {
        return Authorization.byPassword("root", "root");
    }

}
