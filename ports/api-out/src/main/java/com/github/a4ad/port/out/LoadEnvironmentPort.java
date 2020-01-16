package com.github.a4ad.port.out;

public interface LoadEnvironmentPort {

    EnvironmentPortModel loadEnvironment(EnvironmentIdPortModel environmentIdPortModel);

    interface EnvironmentIdPortModel {
        Long getValue();
    }

    interface  EnvironmentPortModel {
        EnvironmentIdPortModel getId();
    }
}
