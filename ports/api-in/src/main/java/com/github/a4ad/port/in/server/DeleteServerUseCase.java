package com.github.a4ad.port.in.server;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;

public interface DeleteServerUseCase {

    void deleteServer(DeleteServerUseCaseCommand command) throws ServerNotFoundRuntimeException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeleteServerUseCaseCommand extends SelfValidating<DeleteServerUseCaseCommand> {

        @NotBlank
        String name;

        public DeleteServerUseCaseCommand(String name) {
            this.name = name;
            validateSelf();
        }

    }

    class ServerNotFoundRuntimeException extends RuntimeException {
        public ServerNotFoundRuntimeException(DeleteServerUseCaseCommand command) {
            super("Server with name '" + command.getName() + "' not found");
        }
    }

}
