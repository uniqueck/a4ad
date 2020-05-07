package com.github.a4ad.adapters.web.ui.controller.admin.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerWebModel {

    String name;
    String ip;
    int port;

    public static ServerWebModel withDefaultPort() {
        return new ServerWebModel("", "", 22);
    }


}
