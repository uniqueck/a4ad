package com.github.a4ad.adapter.persistence.environment;

import com.github.a4ad.port.out.LoadEnvironmentPort.EnvironmentIdPortModel;
import com.github.a4ad.port.out.LoadEnvironmentPort.EnvironmentPortModel;
import lombok.Value;

@Value
class EnvironmentPortAdapterModel implements EnvironmentPortModel {

        @Value
        static class EnvironmentIdPortAdapterModel implements EnvironmentIdPortModel {
            Long value;
        }

        EnvironmentIdPortModel id;

}
