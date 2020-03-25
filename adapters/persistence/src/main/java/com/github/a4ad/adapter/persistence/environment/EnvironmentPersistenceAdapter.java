package com.github.a4ad.adapter.persistence.environment;

import com.github.a4ad.adapter.persistence.repositories.EnvironmentJpaRepository;
import com.github.a4ad.adapter.persistence.repositories.EnvironmentJpaRepository.EnvironmentJpaEntity;
import com.github.a4ad.common.PersistenceAdapter;
import com.github.a4ad.port.out.LoadEnvironmentPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class EnvironmentPersistenceAdapter implements LoadEnvironmentPort {

    private final EnvironmentJpaRepository environmentJpaRepository;

    @Override
    public EnvironmentPortModel loadEnvironment(EnvironmentIdPortModel environmentIdPortModel) {
        Optional<EnvironmentJpaEntity> environmentJpaRepositoryById = this.environmentJpaRepository.findById(environmentIdPortModel.getValue());
        return environmentJpaRepositoryById.map(this::map).orElse(null);
    }

    private EnvironmentPortModel map(EnvironmentJpaEntity from) {
        return new EnvironmentPortAdapterModel(new EnvironmentPortAdapterModel.EnvironmentIdPortAdapterModel(from.getId()));
    }
}
