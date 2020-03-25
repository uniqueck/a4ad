package com.github.a4ad.aceptance;

import com.github.a4ad.port.out.deployment.StartDeploymentProcessPort;
import com.github.a4ad.port.out.deployment.StartDeploymentProcessPort.DeploymentTriggerId;
import com.github.a4ad.port.out.deployment.StartDeploymentProcessPort.StartDeploymentProcessCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class StartDeploymentProcessTest {

    @Container
    private GenericContainer<?> container = new GenericContainer<>("arvindr226/alpine-ssh");

    @BeforeEach
    void beforeEach() {
        assertTrue(container.isRunning());
    }

    protected String deploymentDestinationContainerIpAddress() {
        return container.getContainerIpAddress();
    }

    protected int deploymentDestinationContainerPort() {
        return container.getMappedPort(22);
    }


    @Autowired
    StartDeploymentProcessPort startDeploymentProcessPort;

    @Sql("StartDeploymentProcessTest.sql")
    @Test
    void test() {
        startDeploymentProcessPort.startDeployment(new StartDeploymentProcessCommand(new DeploymentTriggerId(1L)));
    }

}
