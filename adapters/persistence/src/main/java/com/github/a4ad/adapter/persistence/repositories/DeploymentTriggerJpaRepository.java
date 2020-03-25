package com.github.a4ad.adapter.persistence.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

public interface DeploymentTriggerJpaRepository extends JpaRepository<DeploymentTriggerJpaRepository.DeploymentTriggerJpaEntity, Long> {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Table(name = "DEPLOYMENT_TRIGGER")
    @Entity
    class DeploymentTriggerJpaEntity {

        @Id
        @Column(name = "ID")
        @GeneratedValue
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ARTIFACT_ID")
        private ArtifactJpaRepository.ArtifactJpaEntity artifact;

    }

}
