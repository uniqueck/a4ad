package com.github.a4ad.adapter.ssh;

import com.github.a4ad.port.out.ssh.CopyArtifactToDeploymentDestinationPort;
import com.jcabi.log.Logger;
import com.jcabi.ssh.Shell;
import com.jcabi.ssh.Ssh;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;

@Component
class CopyArtifactToDeploymentDestinationAdapter extends AbstractSshAdapter implements CopyArtifactToDeploymentDestinationPort {

    @Override
    public void copyArtifact(CopyArtifactToDeploymentDestinationCommand command) throws CopyArtifactToDeploymentDestinationException {
        try {
            Shell shell = createShell(command.getDestination(), command.getAuthorization());
            if (existParentDirectory(shell, command.getDestinationPath())) {
                if (existArtifactOnDestinationPath(shell, command.getDestinationPath())) {
                    throw new CopyArtifactToDeploymentDestinationException("Existing file on '" + command.getDestinationPath() + "'");
                } else {
                    new Shell.Safe(shell).exec(String.format("cat > %s", Ssh.escape(command.getDestinationPath())), command.getInputStream(), Logger.stream(Level.ALL, true), Logger.stream(Level.ALL, true));
                }
            } else {
                throw new CopyArtifactToDeploymentDestinationException("Directory '" + getParentDirectory(command.getDestinationPath()) + "' for artifact to copy doesn't exist");
            }
        } catch (ShellConnectionException e) {
            throw new CopyArtifactToDeploymentDestinationException(e.getMessage(), e.getCause());
        } catch (IOException e) {
            throw new CopyArtifactToDeploymentDestinationException("Error on copy artifact to host '" + command.getDestination().getHost() + "'" , e);
        }
    }

    protected String getParentDirectory(String path) {
        return path.substring(0, path.lastIndexOf('/'));
    }

    protected boolean existParentDirectory(Shell shell, String path) {
        try {
            return new Shell.Empty(shell).exec("stat " + Ssh.escape(getParentDirectory(path))) == 0;
        } catch (IOException e) {
            return false;
        }
    }

    protected boolean existArtifactOnDestinationPath(Shell shell, String path) {
        try {
            return new Shell.Empty(shell).exec("stat " + Ssh.escape(path)) == 0;
        } catch (IOException e) {
            return false;
        }
    }


}
