package com.github.a4ad.adapter.ssh;

import com.github.a4ad.port.out.ssh.Authorization;
import com.github.a4ad.port.out.ssh.ExecuteCommandOnDeploymentDestinationPort;
import com.github.a4ad.port.out.ssh.ExecuteCommandOnDeploymentDestinationPort.ExecuteCommandOnDeploymentDestinationCommand;
import com.github.a4ad.port.out.ssh.ExecuteCommandOnDeploymentDestinationPort.ExecuteCommandOnDeploymentDestinationPortException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
class ExecuteCommandOnDeploymentDestinationAdapterTest extends AbstractSshAdapterTest {

    @Test
    void execute_CommandHasExitCodeEqualsZeroAndOptionIgnoreFailureIsSetToFalse_allFine() throws ExecuteCommandOnDeploymentDestinationPortException {
        createUnderTest().execute(createCommand("stat /tmp", ExecuteCommandOnDeploymentDestinationPort.Option.builder().ignoreFailure(false)));
    }

    @Test
    void execute_CommandHasExitCodeEqualsZeroAndOptionIgnoreFailureIsSetToTrue_allFine() throws ExecuteCommandOnDeploymentDestinationPortException {
        createUnderTest().execute(createCommand("stat /tmp", ExecuteCommandOnDeploymentDestinationPort.Option.builder().ignoreFailure(true)));
    }


    @Test
    void execute_CommandHasExitCodeGreaterThanZeroAndOptionIgnoreFailureIsSetToTrue_allFine() throws ExecuteCommandOnDeploymentDestinationPortException {
        createUnderTest().execute(createCommand("stat /nonExisitingFile", ExecuteCommandOnDeploymentDestinationPort.Option.builder().ignoreFailure(true)));
    }

    @Test
    void execute_CommandHasExitCodeGreaterThanZeroAndOptionIgnoreFailureIsSetToFalse_ThrowsException() throws ExecuteCommandOnDeploymentDestinationPortException {
        ExecuteCommandOnDeploymentDestinationPortException exception = assertThrows(ExecuteCommandOnDeploymentDestinationPortException.class, () ->
                createUnderTest().execute(createCommand("stat /nonExisitingFile", ExecuteCommandOnDeploymentDestinationPort.Option.builder().ignoreFailure(false))));
        assertEquals("Error on execute command 'stat /nonExisitingFile'", exception.getMessage());
    }

    protected ExecuteCommandOnDeploymentDestinationAdapter createUnderTest() {
        return new ExecuteCommandOnDeploymentDestinationAdapter();
    }

    protected ExecuteCommandOnDeploymentDestinationCommand createCommand(String command, ExecuteCommandOnDeploymentDestinationPort.Option.OptionBuilder optionBuilder) {
        return new ExecuteCommandOnDeploymentDestinationCommand(createDestination(),createAuthorization(),new ExecuteCommandOnDeploymentDestinationPort.Command(command), optionBuilder.build());
    }

    @DisplayName("login with wrong users should throws exception")
    @Test
    void wrongUser() {
        ExecuteCommandOnDeploymentDestinationCommand command = new ExecuteCommandOnDeploymentDestinationCommand(createDestination(), Authorization.byPassword("wrongUser", "root"), createDefaultCommand2Execute(), createDefaultOptions());
        ExecuteCommandOnDeploymentDestinationPortException exception = assertThrows(ExecuteCommandOnDeploymentDestinationPortException.class, () -> createUnderTest().execute(command));
        assertThat(exception.getMessage()).isEqualTo("Error on login to host 'localhost' with user '"+command.getAuthorization().getUsername()+"'").as("exception message should point to all relevant informations");
    }

    private ExecuteCommandOnDeploymentDestinationPort.Command createDefaultCommand2Execute() {
        return new ExecuteCommandOnDeploymentDestinationPort.Command("stat /tmp");
    }

    private ExecuteCommandOnDeploymentDestinationPort.Option createDefaultOptions() {
        return ExecuteCommandOnDeploymentDestinationPort.Option.builder().build();
    }

    @DisplayName("login with wrong password should throws exception, but shows not the password in the exception")
    @Test
    void wrongPassword() {
        ExecuteCommandOnDeploymentDestinationCommand command = new ExecuteCommandOnDeploymentDestinationCommand(createDestination(), Authorization.byPassword("root", "wrongPassword"), createDefaultCommand2Execute(), createDefaultOptions());
        ExecuteCommandOnDeploymentDestinationPortException exception = assertThrows(ExecuteCommandOnDeploymentDestinationPortException.class, () -> createUnderTest().execute(command));
        assertThat(exception.getMessage()).isEqualTo("Error on login to host 'localhost' with user '"+command.getAuthorization().getUsername()+"'").as("exception message should point to all relevant informations");
    }
}