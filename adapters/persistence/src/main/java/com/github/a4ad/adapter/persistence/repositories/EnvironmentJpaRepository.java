package com.github.a4ad.adapter.persistence.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

public interface EnvironmentJpaRepository extends JpaRepository<EnvironmentJpaRepository.EnvironmentJpaEntity, Long> {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Table(name = "ENVIRONMENT")
    @Entity
    class EnvironmentJpaEntity {

        @Id
        @GeneratedValue
        private Long id;
    }


}
