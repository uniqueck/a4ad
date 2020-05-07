package com.github.a4ad.port.in.server;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public interface AddServerUseCase {

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddServerUseCaseCommand extends SelfValidating<AddServerUseCaseCommand> {

        @NotBlank
        String name;

        @NotBlank
        String ip;

        @Min(1)
        int port;

        public AddServerUseCaseCommand(String name, String ip, int port) {
            this.name = name;
            this.ip = ip;
            this.port = port;
            validateSelf();
        }

    }

    boolean addServer(AddServerUseCaseCommand command);

}
