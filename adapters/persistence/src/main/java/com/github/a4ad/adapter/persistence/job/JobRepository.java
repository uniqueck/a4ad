package com.github.a4ad.adapter.persistence.job;

import org.springframework.data.jpa.repository.JpaRepository;

interface JobRepository extends JpaRepository<JobJpaEntity, Long> {
}
