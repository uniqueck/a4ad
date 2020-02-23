package com.github.a4ad.port.out;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface CopyArtifactToDeploymentDestinationPort {

    void copyArtifact(CopyArtifactToDeploymentDestinationCommand command) throws CopyArtifactToDeploymentDestinationException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class CopyArtifactToDeploymentDestinationCommand extends SelfValidating<CopyArtifactToDeploymentDestinationCommand> {

        @NotNull
        Destination destination;
        @NotNull
        Authorization authorization;

        @NotNull
        String destinationPath;

        public static CopyArtifactToDeploymentDestinationCommand of(Destination destination, Authorization authorization, String destinationPath) {
            return new CopyArtifactToDeploymentDestinationCommand(destination, authorization, destinationPath);
        }

        private CopyArtifactToDeploymentDestinationCommand(Destination destination, Authorization authorization, String destinationPath) {
            this.destination = destination;
            this.authorization = authorization;
            this.destinationPath = destinationPath;
            validateSelf();
        }




    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    class Destination extends SelfValidating<Destination> {

        @NotNull
        final String host;
        @Min(1)
        final int port;

        private Destination(String host, int port) {
            this.host = host;
            this.port = port;
            validateSelf();
        }

        public static Destination of(String host, int port) {
            return new Destination(host, port);
        }

    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    class Authorization extends SelfValidating<Authorization> {

        @NotBlank
        final String username;
        @NotBlank
        String password;


        private Authorization(String username, String password) {
            this.username = username;
            this.password = password;
            validateSelf();
        }

        public static Authorization byPassword(String username, String password) {
            return new Authorization(username, password);
        }
    }

    class CopyArtifactToDeploymentDestinationException extends Exception {

        public CopyArtifactToDeploymentDestinationException(String message) {
            super(message);
        }

        public CopyArtifactToDeploymentDestinationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
