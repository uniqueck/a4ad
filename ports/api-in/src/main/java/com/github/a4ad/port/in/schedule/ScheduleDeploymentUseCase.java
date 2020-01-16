package com.github.a4ad.port.in.schedule;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

public interface ScheduleDeploymentUseCase {

    void scheduleDeployment(ScheduleDeploymentCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ScheduleDeploymentCommand extends SelfValidating<ScheduleDeploymentCommand> {
        @NotNull  final EnvironmentId targetEnvironmentId;

        public ScheduleDeploymentCommand(EnvironmentId targetEnvironmentId) {
            this.targetEnvironmentId = targetEnvironmentId;
            this.validateSelf();
        }

    }

    @Value
    class EnvironmentId {
        @NotNull final Long id;
    }

}
