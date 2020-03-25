package com.github.a4ad.adapter.bpmn;

import com.github.a4ad.port.out.deployment.StartDeploymentProcessPort;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
class StartDeploymentProcessFlowableAdapter implements StartDeploymentProcessPort {

    @Autowired
    private final RuntimeService runtimeService;

    @Override
    public String startDeployment(StartDeploymentProcessCommand command) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("applicationContainerDeployment", prozessVariablesBasedOn(command));
        return processInstance.getId();
    }

    protected Map<String, Object> prozessVariablesBasedOn(StartDeploymentProcessCommand command) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("deploymentTriggerId", command.getDeploymentTriggerId().getValue());
        return variables;
    }
}
