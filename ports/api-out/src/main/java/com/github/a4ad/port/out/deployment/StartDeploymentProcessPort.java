package com.github.a4ad.port.out.deployment;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Min;

public interface StartDeploymentProcessPort {

    String startDeployment(StartDeploymentProcessCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class StartDeploymentProcessCommand extends SelfValidating<StartDeploymentProcessCommand> {

        @NonNull
        DeploymentTriggerId deploymentTriggerId;

        public StartDeploymentProcessCommand(DeploymentTriggerId deploymentTriggerId) {
            this.deploymentTriggerId = deploymentTriggerId;
            validateSelf();
        }

    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeploymentTriggerId extends SelfValidating<DeploymentTriggerId> {

        @Min(1)
        Long value;

        public DeploymentTriggerId(Long value) {
            this.value = value;
            validateSelf();
        }

    }


}
