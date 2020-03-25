package com.github.a4ad.application.deployment;

import com.github.a4ad.common.UseCase;
import com.github.a4ad.port.in.deployment.DeploymentStepCopyArtifactPort;
import com.github.a4ad.port.out.LoadArtifact4DeploymentPort;
import com.github.a4ad.port.out.LoadArtifact4DeploymentPort.LoadArtifact4DeploymentCommand;
import com.github.a4ad.port.out.logging.LoggingPort;
import com.github.a4ad.port.out.ssh.CopyArtifactToDeploymentDestinationPort;
import com.github.a4ad.port.out.ssh.CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationCommand;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@UseCase
@RequiredArgsConstructor
class DeploymentStepCopyArtifactService implements DeploymentStepCopyArtifactPort {

    final CopyArtifactToDeploymentDestinationPort copyArtifactToDeploymentDestinationPort;

    final LoadArtifact4DeploymentPort loadArtifact4DeploymentPort;

    @Inject
    LoggingPort logger;


    @Override
    public boolean copyArtifactToDeploymentDestination(DeploymentStepCopyArtifactCommand command) {
        logger.info(() -> "start step: copy artifact to deployment destination");

        LoadArtifact4DeploymentPort.LoadArtifact4DeploymentResponse response = loadArtifact4DeploymentPort.loadArtifact4Deployment(new LoadArtifact4DeploymentCommand(new LoadArtifact4DeploymentPort.DeploymentTriggerId(command.getDeploymentTriggerId().getValue())));
        if (response != null) {
            try {
                copyArtifactToDeploymentDestinationPort.copyArtifact(new CopyArtifactToDeploymentDestinationCommand(null, null, "/tmp", response.getArtifact().getArtifactInputStream()));
            } catch (CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationException e) {
                logger.info(() -> "copy artifact fails");
                return false;
            }
        } else {
            logger.info(() -> "artifact doesn't exist");
            return false;
        }


        return true;
    }
}
