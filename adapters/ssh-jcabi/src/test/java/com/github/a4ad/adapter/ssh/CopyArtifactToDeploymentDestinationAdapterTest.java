package com.github.a4ad.adapter.ssh;

import com.github.a4ad.port.out.ssh.Authorization;
import com.github.a4ad.port.out.ssh.CopyArtifactToDeploymentDestinationPort;
import com.github.a4ad.port.out.ssh.CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationCommand;
import com.github.a4ad.port.out.ssh.Destination;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class CopyArtifactToDeploymentDestinationAdapterTest extends AbstractSshAdapterTest {


    @DisplayName("copy artifact to destination machine")
    @Test
    void copyArtifact() throws Exception {
        CopyArtifactToDeploymentDestinationCommand command = createDefaultCommand();
        new CopyArtifactToDeploymentDestinationAdapter().copyArtifact(command);
        ExecResult execResult = execInContainer("stat", command.getDestinationPath());
        assertEquals(0, execResult.getExitCode());
        assertTrue(execResult.getStdout().contains("File: " + command.getDestinationPath()));
        execResult = execInContainer("cat", command.getDestinationPath());
        assertEquals(0, execResult.getExitCode());
        assertEquals("content", execResult.getStdout());
    }

    @NotNull
    private CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationCommand createDefaultCommand() {
        return createCommand("root", "root", "/tmp/deployFile.txt");
    }

    @NotNull
    private CopyArtifactToDeploymentDestinationCommand createCommand(String user, String password, String pathToArtifactOnServer) {
        return createCommand(user, password, containerIpAddress(), containerPort(), pathToArtifactOnServer);
    }

    @NotNull
    private CopyArtifactToDeploymentDestinationCommand createCommand(String user, String password, String ipAddress, int port, String pathToArtifactOnServer) {
        return CopyArtifactToDeploymentDestinationCommand.of(Destination.of(ipAddress, port), Authorization.byPassword(user, password), pathToArtifactOnServer, new ByteArrayInputStream("content".getBytes(StandardCharsets.UTF_8)));
    }

    @NotNull
    private CopyArtifactToDeploymentDestinationCommand createCommandWithWrongUser() {
        return createCommand("wrongUser", "root", "/tmp/deployFile.txt");
    }

    @NotNull
    private CopyArtifactToDeploymentDestinationCommand createCommandWithWrongPassword() {
        return createCommand("root", "wrongPassword", "/tmp/deployFile.txt");
    }

    @NotNull
    private CopyArtifactToDeploymentDestinationCommand createCommandWithNoneExistingPath() {
        return createCommand("root", "root", "/tmp/nonexisitingdirectory/deployFile.txt");
    }

    @DisplayName("login with wrong users should throws exception")
    @Test
    void wrongUser() {
        CopyArtifactToDeploymentDestinationCommand command = createCommandWithWrongUser();
        CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException exception = assertThrows(CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException.class, () -> new CopyArtifactToDeploymentDestinationAdapter().copyArtifact(command));
        assertThat(exception.getMessage()).isEqualTo("Error on login to host 'localhost' with user '"+command.getAuthorization().getUsername()+"'").as("exception message should point to all relevant informations");
    }

    @DisplayName("login with wrong password should throws exception, but shows not the password in the exception")
    @Test
    void wrongPassword() {
        CopyArtifactToDeploymentDestinationCommand command = createCommandWithWrongPassword();
        CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException exception = assertThrows(CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException.class, () -> new CopyArtifactToDeploymentDestinationAdapter().copyArtifact(command));
        assertThat(exception.getMessage()).isEqualTo("Error on login to host 'localhost' with user '"+command.getAuthorization().getUsername()+"'").as("exception message should point to all relevant informations");
    }

    @DisplayName("copy artifact to a none existing directory should throws exception")
    @Test
    void wrongPath() {
        CopyArtifactToDeploymentDestinationCommand command = createCommandWithNoneExistingPath();
        CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException exception = assertThrows(CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException.class, () -> new CopyArtifactToDeploymentDestinationAdapter().copyArtifact(command));
        assertThat(exception.getMessage()).isEqualTo("Directory '"+command.getDestinationPath().substring(0, command.getDestinationPath().lastIndexOf("/"))+"' for artifact to copy doesn't exist").as("exception message should point to all relevant informations");
    }

    @DisplayName("artifact exist on destination, copy should throws exception")
    @Test
    void existingArtifactOnDestination() throws IOException, InterruptedException {
        CopyArtifactToDeploymentDestinationCommand command = createDefaultCommand();
        execInContainer("touch", command.getDestinationPath());
        CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException exception = assertThrows(CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException.class, () -> new CopyArtifactToDeploymentDestinationAdapter().copyArtifact(command));
        assertThat(exception.getMessage()).isEqualTo("Existing file on '"+command.getDestinationPath()+"'").as("exception message should point to all relevant informations");
    }

    @DisplayName("wrong port specified, copy should throws exception")
    @Test
    void wrongPort() throws IOException, InterruptedException {
        CopyArtifactToDeploymentDestinationCommand command = createCommand("root", "root", containerIpAddress(),9999,"/tmp/deployFile.txt");
        CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException exception = assertThrows(CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException.class, () -> new CopyArtifactToDeploymentDestinationAdapter().copyArtifact(command));
        assertThat(exception.getMessage()).isEqualTo("Connection to destination '"+command.getDestination().getHost()+":" + command.getDestination().getPort() +"' couldn't be established").as("exception message should point to all relevant informations");
    }



}