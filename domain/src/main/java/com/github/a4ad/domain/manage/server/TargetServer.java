package com.github.a4ad.domain.manage.server;

import lombok.Value;

@Value
public class TargetServer {

    TargetServerName serverName;
    TargetServerPort serverPort;
    Authentication authentication;


    @Value
    public static class TargetServerName {
        String value;
    }

    @Value
    public static class TargetServerPort {
        int value;
    }

}
