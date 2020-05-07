package com.github.a4ad.application.admin.server;

import com.github.a4ad.common.Query;
import com.github.a4ad.port.in.server.LoadServerQuery;
import com.github.a4ad.port.out.persistence.ListServersPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Query
class LoadServerService implements LoadServerQuery {

    final ListServersPort listServersPort;


    @Override
    public List<LoadServerQueryModel> loadServer() {
        return listServersPort.loadServers().stream().map(this::map).collect(Collectors.toList());
    }

    private LoadServerQueryModel map(ListServersPort.ListServersPortModel toMap) {
        return new LoadServerQueryModel(toMap.getName(), toMap.getIp(), toMap.getPort());
    }
}
