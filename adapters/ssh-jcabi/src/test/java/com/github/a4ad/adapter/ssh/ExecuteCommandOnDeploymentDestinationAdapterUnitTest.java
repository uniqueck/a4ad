package com.github.a4ad.adapter.ssh;

import com.github.a4ad.port.out.ssh.Authorization;
import com.github.a4ad.port.out.ssh.Destination;
import com.github.a4ad.port.out.ssh.ExecuteCommandOnDeploymentDestinationPort;
import com.github.a4ad.port.out.ssh.ExecuteCommandOnDeploymentDestinationPort.ExecuteCommandOnDeploymentDestinationCommand;
import com.jcabi.ssh.Shell;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExecuteCommandOnDeploymentDestinationAdapterUnitTest {

    @Test
    void test_exceptionOnExecuteCommandIgnoreOnFailureIsSetToFalse_ThrowsException() throws ExecuteCommandOnDeploymentDestinationPort.ExecuteCommandOnDeploymentDestinationPortException {
        ExecuteCommandOnDeploymentDestinationCommand command = createDefaultCommand(false);
        ExecuteCommandOnDeploymentDestinationPort.ExecuteCommandOnDeploymentDestinationPortException exception = assertThrows(ExecuteCommandOnDeploymentDestinationPort.ExecuteCommandOnDeploymentDestinationPortException.class, () ->
                new ExecuteCommandOnDeploymentDestinationAdapter() {

                    @Override
                    protected Shell createShell(Destination destination, Authorization authorization) throws ShellConnectionException {
                        return new Shell() {
                            @Override
                            public int exec(String command, InputStream stdin, OutputStream stdout, OutputStream stderr) throws IOException {
                                throw new IOException("Error on Command");
                            }
                        };
                    }
                }.execute(command));
        assertThat(exception).hasMessage("Error on execute command 'whoami'").hasCause(new IOException("Error on Command"));
    }

    @Test
    void test_exceptionOnExecuteCommandIgnoreOnFailureIsSetToTrue_AllFine() {
        ExecuteCommandOnDeploymentDestinationCommand command = createDefaultCommand(true);
        assertDoesNotThrow(() ->
                new ExecuteCommandOnDeploymentDestinationAdapter() {

                    @Override
                    protected Shell createShell(Destination destination, Authorization authorization) throws ShellConnectionException {
                        return new Shell() {
                            @Override
                            public int exec(String command, InputStream stdin, OutputStream stdout, OutputStream stderr) throws IOException {
                                throw new IOException("Error on Command");
                            }
                        };
                    }
                }.execute(command));
    }

    @NotNull
    private ExecuteCommandOnDeploymentDestinationPort.ExecuteCommandOnDeploymentDestinationCommand createDefaultCommand(boolean ignoreOnFailure) {
        return new ExecuteCommandOnDeploymentDestinationCommand(Destination.of("localhost", 22), Authorization.byPassword("root", "root"), new ExecuteCommandOnDeploymentDestinationPort.Command("whoami"), ExecuteCommandOnDeploymentDestinationPort.Option.builder().ignoreFailure(ignoreOnFailure).build());
    }

}
