package com.github.a4ad.application.admin.server;

import com.github.a4ad.common.UseCase;
import com.github.a4ad.port.in.server.AddServerUseCase;
import com.github.a4ad.port.out.persistence.SaveServerPort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class ServerService implements AddServerUseCase {

    @Getter(AccessLevel.PROTECTED) final SaveServerPort saveServerPort;

    @Override
    public boolean addServer(AddServerUseCaseCommand command) {
        saveServerPort.saveServer(new SaveServerPort.SaveServerPortModel(command.getName(), command.getIp(), command.getPort()));
        return true;
    }
}
