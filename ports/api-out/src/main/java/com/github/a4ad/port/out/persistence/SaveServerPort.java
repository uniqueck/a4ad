package com.github.a4ad.port.out.persistence;

import lombok.Value;

public interface SaveServerPort {

    @Value
    class SaveServerPortModel {
        String name;
        String ip;
        int port;
    }

    void saveServer(SaveServerPortModel model);

}
