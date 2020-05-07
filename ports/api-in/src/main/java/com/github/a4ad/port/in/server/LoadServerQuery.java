package com.github.a4ad.port.in.server;

import lombok.Value;

import java.util.List;

public interface LoadServerQuery {

    @Value
    class LoadServerQueryModel {
        String name;
        String ip;
        int port;
    }

    List<LoadServerQueryModel> loadServer();

}
