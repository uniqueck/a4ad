package com.github.a4ad.application.admin.server;

import com.github.a4ad.common.UseCase;
import com.github.a4ad.port.in.server.DeleteServerUseCase;
import com.github.a4ad.port.out.persistence.DeleteServerPort;
import com.github.a4ad.port.out.persistence.LoadServerPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class DeleteServerService implements DeleteServerUseCase {

    final LoadServerPort loadServerPort;
    final DeleteServerPort deleteServerPort;

    @Override
    public void deleteServer(DeleteServerUseCaseCommand command) {
        LoadServerPort.LoadServerPortModel serverPortModel = loadServerPort.loadServerByName(command.getName());
        if (serverPortModel != null) {
            deleteServerPort.deleteServer(command.getName());
        } else {
            throw new ServerNotFoundRuntimeException(command);
        }
    }
}
