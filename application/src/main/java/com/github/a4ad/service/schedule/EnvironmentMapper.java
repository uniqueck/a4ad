package com.github.a4ad.service.schedule;

import com.github.a4ad.port.in.schedule.ScheduleDeploymentUseCase;
import com.github.a4ad.port.out.LoadEnvironmentPort;
import lombok.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
class EnvironmentMapper {

    @Value
    static class EnvironmentIdPortModelImpl implements LoadEnvironmentPort.EnvironmentIdPortModel {
        private final Long value;
    }

    LoadEnvironmentPort.EnvironmentIdPortModel mapToPortModel(ScheduleDeploymentUseCase.@NotNull EnvironmentId targetEnvironmentId) {
        return new EnvironmentIdPortModelImpl(targetEnvironmentId.getId());
    }


}
