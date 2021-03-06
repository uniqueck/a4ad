package com.github.a4ad.adapter.persistence.job;

import com.github.a4ad.port.out.LoadJobPort;
import org.springframework.stereotype.Component;

@Component
class JobMapper {

    public LoadJobPort.JobPortModel mapToPortModel(JobJpaEntity from) {

        return new JobPortTransferModel(new JobPortTransferModel.JobIdPortTransferModel(from.getId()), from.getName());

    }

}
