package com.github.a4ad.adapter.bpmn.service;

import com.github.a4ad.port.in.deployment.DeploymentStepCopyArtifactPort;
import com.github.a4ad.port.in.deployment.DeploymentStepCopyArtifactPort.DeploymentStepCopyArtifactCommand;
import com.github.a4ad.port.in.deployment.DeploymentStepCopyArtifactPort.DeploymentTriggerId;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@FlowableJavaDelegate("pushArtifactToDeploymentDestinationFlowableJavaDelegate")
class PushArtifactToDeploymentDestinationFlowableJavaDelegate implements JavaDelegate {

    @Autowired
    DeploymentStepCopyArtifactPort delegatePort;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        String currentActivityId = delegateExecution.getCurrentActivityId();
        Map<String, Object> variables = delegateExecution.getVariables();

        Long deploymentTriggerId = (Long) variables.get("deploymentTriggerId");

        delegatePort.copyArtifactToDeploymentDestination(createCommand(deploymentTriggerId));


    }

    protected DeploymentStepCopyArtifactCommand createCommand(Long deploymentTriggerId) {
        return new DeploymentStepCopyArtifactCommand(new DeploymentTriggerId(deploymentTriggerId));
    }
}
