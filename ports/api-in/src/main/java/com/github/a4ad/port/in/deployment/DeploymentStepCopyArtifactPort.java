package com.github.a4ad.port.in.deployment;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface DeploymentStepCopyArtifactPort {

    boolean copyArtifactToDeploymentDestination(DeploymentStepCopyArtifactCommand command);


    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeploymentStepCopyArtifactCommand extends SelfValidating<DeploymentStepCopyArtifactCommand> {

        @NotNull
        DeploymentTriggerId deploymentTriggerId;

        public DeploymentStepCopyArtifactCommand(DeploymentTriggerId deploymentTriggerId) {
            this.deploymentTriggerId = deploymentTriggerId;
            validateSelf();
        }


    }

    @Value
    class DeploymentTriggerId extends SelfValidating<DeploymentTriggerId> {
        @NotNull
        @Min(1)
        Long value;

        public DeploymentTriggerId(Long value) {
            this.value = value;
            validateSelf();
        }

    }

}
