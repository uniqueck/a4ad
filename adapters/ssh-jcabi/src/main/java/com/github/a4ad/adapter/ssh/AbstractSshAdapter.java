package com.github.a4ad.adapter.ssh;

import com.github.a4ad.port.out.ssh.Authorization;
import com.github.a4ad.port.out.ssh.Destination;
import com.jcabi.ssh.Shell;
import com.jcabi.ssh.SshByPassword;
import com.jcraft.jsch.JSchException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

abstract class AbstractSshAdapter {


    class ShellConnectionException extends Exception {

        public ShellConnectionException(String message) {
            super(message);
        }

        public ShellConnectionException(String message, Throwable cause) {
            super(message, cause);
        }

    }

    protected Shell createShell(Destination destination, Authorization authorization) throws ShellConnectionException {
        try {
            SshByPassword shell = new SshByPassword(destination.getHost(), destination.getPort(), authorization.getUsername(), authorization.getPassword());
            new Shell.Empty(shell).exec("whoami");
            return shell;
        } catch (UnknownHostException e) {
            throw new ShellConnectionException("Host '" + destination.getHost() + "' can't resolved",e);
        } catch (IOException e) {
            if (e.getCause() instanceof JSchException) {
                JSchException cause = (JSchException) e.getCause();
                if (cause.getCause() instanceof ConnectException) {
                    throw new ShellConnectionException("Connection to destination '" + destination.getHost() + ":" + destination.getPort() + "' couldn't be established", e);
                } else if ("Auth fail".equals(cause.getMessage())) {
                    throw new ShellConnectionException("Error on login to host '" + destination.getHost() + "' with user '" + authorization.getUsername() + "'");
                }
            }
            throw new ShellConnectionException("Error on connect to destination '" + destination.getHost() + ":" + destination.getPort() + "'",e);
        }
    }
}
