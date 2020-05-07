package com.github.a4ad.adapter.persistence.server;

import com.github.a4ad.adapter.persistence.repositories.ServerJpaRepository;
import com.github.a4ad.common.PersistenceAdapter;
import com.github.a4ad.port.out.persistence.DeleteServerPort;
import com.github.a4ad.port.out.persistence.ListServersPort;
import com.github.a4ad.port.out.persistence.LoadServerPort;
import com.github.a4ad.port.out.persistence.SaveServerPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@PersistenceAdapter
class ServerPersistenceAdapter implements SaveServerPort, LoadServerPort, DeleteServerPort, ListServersPort {

    final ServerJpaRepository serverJpaRepository;

    @Override
    public void saveServer(SaveServerPortModel model) {
        serverJpaRepository.save(map(model));
    }

    @Override
    public List<ListServersPortModel> loadServers() {
        return serverJpaRepository.findAll().stream().map(this::mapListServersPortModel).collect(Collectors.toList());
    }

    @Override
    public LoadServerPortModel loadServerByName(String name) {
        return serverJpaRepository.findByName(name).map(this::mapLoadServerPortModel).orElse(null);
    }

    private LoadServerPortModel mapLoadServerPortModel(ServerJpaRepository.ServerJpaEntity toMap) {
        return new LoadServerPortModel(toMap.getName(), toMap.getIp(), toMap.getPort());
    }

    private ListServersPortModel mapListServersPortModel(ServerJpaRepository.ServerJpaEntity toMap) {
        return new ListServersPortModel(toMap.getName(), toMap.getIp(), toMap.getPort());
    }

    private ServerJpaRepository.ServerJpaEntity map(SaveServerPortModel toMap) {
        return new ServerJpaRepository.ServerJpaEntity(-1L, toMap.getName(), toMap.getIp(), toMap.getPort());
    }

    @Override
    public void deleteServer(String name) {
        serverJpaRepository.deleteByName(name);
    }
}
