package com.github.a4ad.adapter.persistence.repositories;

import com.github.a4ad.adapter.persistence.repositories.DeploymentTriggerJpaRepository.DeploymentTriggerJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.List;

public interface ArtifactJpaRepository extends JpaRepository<ArtifactJpaRepository.ArtifactJpaEntity, Long> {


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Table(name = "ARTIFACT")
    @Entity
    class ArtifactJpaEntity {

        @Id
        @Column(name = "ID")
        @GeneratedValue
        private Long id;

        @Column(name = "LOCATION")
        private String location;

        @OneToMany(mappedBy = "artifact")
        private List<DeploymentTriggerJpaEntity> deploymentTrigger;

    }

}
