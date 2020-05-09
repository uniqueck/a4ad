package com.github.a4ad.adapter.persistence.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

public interface ServerJpaRepository extends JpaRepository<ServerJpaRepository.ServerJpaEntity, BigDecimal> {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Table(name = "SERVER")
    @Entity
    class ServerJpaEntity {

        @Id
        @Column(name = "ID")
        @GeneratedValue
        private Long id;

        @Column(name = "NAME", nullable = false, unique = true)
        private String name;

        @Column(name = "IP")
        private String ip;

        @Column(name = "PORT")
        private int port;

    }

    Optional<ServerJpaEntity> findByName(String name);

    void deleteByName(String name);

}
