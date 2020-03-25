package com.github.a4ad.adapter.persistence.loadartifact4deployment;

import com.github.a4ad.adapter.persistence.repositories.ArtifactJpaRepository;
import com.github.a4ad.port.out.LoadArtifact4DeploymentPort;
import com.github.a4ad.port.out.LoadArtifact4DeploymentPort.DeploymentTriggerId;
import com.github.a4ad.port.out.LoadArtifact4DeploymentPort.LoadArtifact4DeploymentCommand;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({LoadArtifact4DeploymentPersistenceAdapter.class})
class LoadArtifact4DeploymentPersistenceAdapterTest {

    @Autowired
    LoadArtifact4DeploymentPersistenceAdapter underTest;

    @Autowired
    ArtifactJpaRepository artifactJpaRepository;

    @Sql("LoadArtifact4DeploymentPersistenceAdapterTest.sql")
    @Test
    void loadArtifact4Deployment() throws IOException {

        byte[] fileContentAsByteArray = FileUtils.readFileToByteArray(new File("src/test/resources/test.txt"));

        LoadArtifact4DeploymentCommand command = new LoadArtifact4DeploymentCommand(new DeploymentTriggerId(1L));

        LoadArtifact4DeploymentPort.LoadArtifact4DeploymentResponse response = underTest.loadArtifact4Deployment(command);
        assertThat(response).isNotNull();
        byte[] bytes = IOUtils.toByteArray(response.getArtifact().getArtifactInputStream());
        assertThat(bytes).isEqualTo(fileContentAsByteArray);

        command = new LoadArtifact4DeploymentCommand(new DeploymentTriggerId(2L));

        response = underTest.loadArtifact4Deployment(command);
        assertThat(response).as("not existing trigger should return null").isNull();


    }

}