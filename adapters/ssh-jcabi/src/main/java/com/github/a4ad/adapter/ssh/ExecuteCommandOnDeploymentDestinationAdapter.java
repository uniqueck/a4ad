package com.github.a4ad.adapter.ssh;

import com.github.a4ad.port.out.ssh.ExecuteCommandOnDeploymentDestinationPort;
import com.jcabi.log.Logger;
import com.jcabi.ssh.Shell;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;

@Component
class ExecuteCommandOnDeploymentDestinationAdapter extends AbstractSshAdapter implements ExecuteCommandOnDeploymentDestinationPort {

    @Override
    public void execute(ExecuteCommandOnDeploymentDestinationCommand command) throws ExecuteCommandOnDeploymentDestinationPortException {
        try {
            Shell shell = createShell(command);
            int execCode = shell.exec(command.getCommand().getValue(), null, Logger.stream(Level.ALL, true), Logger.stream(Level.ALL, true));
            if (execCode != 0 && shouldThrowAnExceptionOnCommandFailure(command)) {
                throw new ExecuteCommandOnDeploymentDestinationPortException("Error on execute command '" + command.getCommand().getValue() + "'");
            }
        } catch (ExecuteCommandOnDeploymentDestinationPortException e) {
            throw e;
        } catch (IOException e) {
            if (shouldThrowAnExceptionOnCommandFailure(command)) {
                throw new ExecuteCommandOnDeploymentDestinationPortException("Error on execute command '" + command.getCommand().getValue() + "'",e);
            }
        }

    }

    private boolean shouldThrowAnExceptionOnCommandFailure(ExecuteCommandOnDeploymentDestinationCommand command) {
        return !command.getOption().isIgnoreFailure();
    }

    private Shell createShell(ExecuteCommandOnDeploymentDestinationCommand command) throws ExecuteCommandOnDeploymentDestinationPortException {
        try {
            return createShell(command.getDestination(), command.getAuthorization());
        } catch (ShellConnectionException e) {
            throw new ExecuteCommandOnDeploymentDestinationPortException(e.getMessage(), e.getCause());
        }
    }
}
