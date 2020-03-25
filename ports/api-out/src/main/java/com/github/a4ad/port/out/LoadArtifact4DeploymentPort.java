package com.github.a4ad.port.out;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.InputStream;

public interface LoadArtifact4DeploymentPort {

    LoadArtifact4DeploymentResponse loadArtifact4Deployment(@NotNull LoadArtifact4DeploymentCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoadArtifact4DeploymentCommand extends SelfValidating<LoadArtifact4DeploymentCommand> {

        @NotNull
        DeploymentTriggerId deploymentTriggerId;

        public LoadArtifact4DeploymentCommand(DeploymentTriggerId deploymentTriggerId) {
            this.deploymentTriggerId = deploymentTriggerId;
            validateSelf();
        }

    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeploymentTriggerId extends SelfValidating<DeploymentTriggerId> {
        @NotNull
        @Min(1)
        Long value;

        public DeploymentTriggerId(Long value) {
            this.value = value;
            validateSelf();
        }
    }

    @Value
    class LoadArtifact4DeploymentResponse {
        Artifact artifact;
        Destination destination;
        Authentication authentification;
    }

    @Value
    class Artifact {
        InputStream artifactInputStream;
        String deploymentPath;
    }

    @Value
    class Destination {
        String host;
        int port;
    }

    @Value
    class Authentication {
        Username username;
        Password password;
    }

    @Value
    class Username {
        String value;
    }

    @Value
    class Password {
        String value;
    }



}
