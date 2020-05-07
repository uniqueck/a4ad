package com.github.a4ad.application.deployment.execute;

import com.github.a4ad.common.UseCase;
import com.github.a4ad.port.in.deployment.StartDeploymentUseCase;
import com.github.a4ad.port.out.ssh.CopyArtifactToDeploymentDestinationPort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.CompletableFuture;

@UseCase
@RequiredArgsConstructor
class ExecuteDeployment implements StartDeploymentUseCase {


    @Getter(AccessLevel.PROTECTED)  final CopyArtifactToDeploymentDestinationPort copyArtifactToDeploymentDestinationPort;


    @Scheduled
    void startDeployment() {

    }

    @Async
    public CompletableFuture<Object> startDeployment(StartDeploymentUseCaseCommand command) {

        // load environment config

        // push artifact to target servers in parallel
        // getCopyArtifactToDeploymentDestinationPort().copyArtifact(new CopyArtifactToDeploymentDestinationPort.CopyArtifactToDeploymentDestinationCommand());


        return CompletableFuture.completedFuture(null);
    }

}
