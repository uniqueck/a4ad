package com.github.a4ad.adapter.persistence.job;

import com.github.a4ad.common.PersistenceAdapter;
import com.github.a4ad.port.out.LoadJobPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
class JobPersistenceAdapter implements LoadJobPort {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;


    @Override
    public JobPortModel loadJob(JobIdPortModel jobIdPortModel) {

        Optional<JobJpaEntity> jobJpaEntity = this.jobRepository.findById(jobIdPortModel.getValue());

        return jobJpaEntity.map(jobMapper::mapToPortModel).orElse(null);
    }
}

