package com.github.a4ad.port.out;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface LoadDeploymentTriggerPort {

    void loadDeploymentTrigger(LoadDeploymentTriggerCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoadDeploymentTriggerCommand extends SelfValidating<LoadDeploymentTriggerCommand> {

        @NotNull
        DeploymentTriggerId deploymentTriggerId;

        public LoadDeploymentTriggerCommand(DeploymentTriggerId deploymentTriggerId) {
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



}
