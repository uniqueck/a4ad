package com.github.a4ad.adapter.persistence.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "JOB")
@Entity
class JobJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String name;

}
