package com.github.a4ad.adapter.persistence.job;

import com.github.a4ad.port.out.LoadJobPort;
import lombok.Value;

@Value
class JobPortTransferModel implements LoadJobPort.JobPortModel {

    @Value
    static class JobIdPortTransferModel implements LoadJobPort.JobIdPortModel {
        private final Long value;

    }

    private final LoadJobPort.JobIdPortModel id;
    private final String name;


}
