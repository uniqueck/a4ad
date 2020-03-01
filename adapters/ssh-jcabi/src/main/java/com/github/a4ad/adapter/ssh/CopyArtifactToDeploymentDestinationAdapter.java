package com.github.a4ad.adapter.ssh;

import com.github.a4ad.port.out.ssh.CopyArtifactToDeploymentDestinationPort;
import com.jcabi.log.Logger;
import com.jcabi.ssh.Shell;
import com.jcabi.ssh.Ssh;
import com.jcabi.ssh.SshByPassword;
import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.logging.Level;

@Component
class CopyArtifactToDeploymentDestinationAdapter implements CopyArtifactToDeploymentDestinationPort {

    @Override
    public void copyArtifact(CopyArtifactToDeploymentDestinationCommand command) throws CopyArtifactToDeploymentDestinationException {
        try {
            Shell shell = createShell(command.getDestination(), command.getAuthorization());
                if (existParentDirectory(shell, command.getDestinationPath())) {
                    if (existArtifactOnDestinationPath(shell, command.getDestinationPath())) {
                        throw new CopyArtifactToDeploymentDestinationException("Existing file on '" + command.getDestinationPath() + "'");
                    } else {
                        new Shell.Safe(shell).exec(String.format("cat > %s", Ssh.escape(command.getDestinationPath())) , command.getInputStream(), Logger.stream(Level.ALL, true),Logger.stream(Level.ALL, true));
                    }
                } else {
                    throw new CopyArtifactToDeploymentDestinationException("Directory '" + getParentDirectory(command.getDestinationPath()) + "' for artifact to copy doesn't exist");
                }
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


    private Shell createShell(Destination destination, Authorization authorization) throws CopyArtifactToDeploymentDestinationException {
        try {
            SshByPassword shell = new SshByPassword(destination.getHost(), destination.getPort(), authorization.getUsername(), authorization.getPassword());
            new Shell.Empty(shell).exec("whoami");
            return shell;
        } catch (UnknownHostException e) {
            throw new CopyArtifactToDeploymentDestinationException("Host '" + destination.getHost() + "' can't resolved",e);
        } catch (IOException e) {
            if (e.getCause() instanceof JSchException) {
                JSchException cause = (JSchException) e.getCause();
                if (cause.getCause() instanceof ConnectException) {
                    throw new CopyArtifactToDeploymentDestinationException("Connection to destination '" + destination.getHost() + ":" + destination.getPort() + "' couldn't be established", e);
                } else if ("Auth fail".equals(cause.getMessage())) {
                    throw new CopyArtifactToDeploymentDestinationException("Error on login to host '" + destination.getHost() + "' with user '" + authorization.getUsername() + "'");
                }
            }
            throw new CopyArtifactToDeploymentDestinationException("Error on connect to destination '" + destination.getHost() + ":" + destination.getPort() + "'",e);
        }
    }
}
