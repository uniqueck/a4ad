package com.github.a4ad.service.schedule;

import com.github.a4ad.common.UseCase;
import com.github.a4ad.port.in.schedule.ScheduleDeploymentUseCase;
import com.github.a4ad.port.out.LoadEnvironmentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class ScheduleDeploymentService implements ScheduleDeploymentUseCase {

    private final LoadEnvironmentPort loadEnvironmentPort;
    private final EnvironmentMapper environmentMapper;


    @Override
    public void scheduleDeployment(ScheduleDeploymentCommand command) {
        LoadEnvironmentPort.EnvironmentPortModel environmentPortModel = loadEnvironmentPort.loadEnvironment(environmentMapper.mapToPortModel(command.getTargetEnvironmentId()));
    }
}
