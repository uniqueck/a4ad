package com.github.a4ad.adapter.persistence.environment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
