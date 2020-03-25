package com.github.a4ad.adapter.persistence.loadartifact4deployment;

import com.github.a4ad.adapter.persistence.repositories.DeploymentTriggerJpaRepository;
import com.github.a4ad.adapter.persistence.repositories.DeploymentTriggerJpaRepository.DeploymentTriggerJpaEntity;
import com.github.a4ad.common.PersistenceAdapter;
import com.github.a4ad.port.out.LoadArtifact4DeploymentPort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class LoadArtifact4DeploymentPersistenceAdapter implements LoadArtifact4DeploymentPort {

    @Getter(AccessLevel.PRIVATE) private final DeploymentTriggerJpaRepository deploymentTriggerJpaRepository;

    @Override
    public LoadArtifact4DeploymentResponse loadArtifact4Deployment(@NotNull LoadArtifact4DeploymentCommand command) {
        Optional<DeploymentTriggerJpaEntity> triggerJpaEntity = getDeploymentTriggerJpaRepository().findById(command.getDeploymentTriggerId().getValue());
        if (triggerJpaEntity.isPresent()) {
            try {
                Artifact artifact = new Artifact(new FileInputStream(triggerJpaEntity.get().getArtifact().getLocation()), "");
                Destination destination = new Destination("", 0);
                Authentication authentication = new Authentication(new Username(""), new Password(""));
                return new LoadArtifact4DeploymentResponse(artifact, destination, authentication);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

}
