package com.github.a4ad.port.in.schedule;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

public interface ScheduleDeploymentUseCase {

    void scheduleDeployment(ScheduleDeploymentCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ScheduleDeploymentCommand extends SelfValidating<ScheduleDeploymentCommand> {



    }

}
