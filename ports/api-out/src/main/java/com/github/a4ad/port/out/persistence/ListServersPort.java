package com.github.a4ad.port.out.persistence;

import lombok.Value;

import java.util.List;

public interface ListServersPort {

    @Value
    class ListServersPortModel {
        String name;
        String ip;
        int  port;
    }

    List<ListServersPortModel> loadServers();

}
