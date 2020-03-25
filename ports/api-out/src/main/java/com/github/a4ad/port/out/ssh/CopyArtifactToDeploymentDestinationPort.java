package com.github.a4ad.port.out.ssh;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.InputStream;

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

        @NotNull InputStream inputStream;

        public static CopyArtifactToDeploymentDestinationCommand of(Destination destination, Authorization authorization, String destinationPath, InputStream inputStream) {
            return new CopyArtifactToDeploymentDestinationCommand(destination, authorization, destinationPath, inputStream);
        }

        public CopyArtifactToDeploymentDestinationCommand(Destination destination, Authorization authorization, String destinationPath, InputStream inputStream) {
            this.destination = destination;
            this.authorization = authorization;
            this.destinationPath = destinationPath;
            this.inputStream = inputStream;
            validateSelf();
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
