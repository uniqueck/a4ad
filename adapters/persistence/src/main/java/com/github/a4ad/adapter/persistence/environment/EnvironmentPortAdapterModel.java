package com.github.a4ad.adapter.persistence.environment;

import com.github.a4ad.port.out.LoadEnvironmentPort;
import lombok.Value;

@Value
class EnvironmentPortAdapterModel implements LoadEnvironmentPort.EnvironmentPortModel {

        @Value
        static class EnvironmentIdPortAdapterModel implements LoadEnvironmentPort.EnvironmentIdPortModel {
            private Long value;
        }

        private final LoadEnvironmentPort.EnvironmentIdPortModel id;

}
