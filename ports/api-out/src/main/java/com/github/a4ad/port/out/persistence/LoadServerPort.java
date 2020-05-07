package com.github.a4ad.port.out.persistence;

import lombok.Value;

public interface LoadServerPort {

    @Value
    class LoadServerPortModel {
        String name;
        String ip;
        int  port;
    }

    LoadServerPortModel loadServerByName(String name);

}
