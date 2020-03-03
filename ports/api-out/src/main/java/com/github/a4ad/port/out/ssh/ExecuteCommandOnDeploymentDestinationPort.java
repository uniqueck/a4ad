package com.github.a4ad.port.out.ssh;

import com.github.a4ad.common.SelfValidating;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface ExecuteCommandOnDeploymentDestinationPort {

    class ExecuteCommandOnDeploymentDestinationPortException extends Exception {

        public ExecuteCommandOnDeploymentDestinationPortException(String message, Throwable cause) {
            super(message, cause);
        }

        public ExecuteCommandOnDeploymentDestinationPortException(String message) {
            super(message);
        }

    }

    void execute(ExecuteCommandOnDeploymentDestinationCommand command) throws ExecuteCommandOnDeploymentDestinationPortException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ExecuteCommandOnDeploymentDestinationCommand extends SelfValidating<ExecuteCommandOnDeploymentDestinationCommand> {

        @NotNull
        final Destination destination;

        @NotNull
        final Authorization authorization;

        @NotNull
        final Command command;

        @NotNull
        final Option option;

        public ExecuteCommandOnDeploymentDestinationCommand(Destination destination, Authorization authorization, Command command, Option option) {
            this.destination = destination;
            this.authorization = authorization;
            this.command = command;
            this.option = option;
            validateSelf();
        }



    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    class Command extends SelfValidating<Command> {

        @NotBlank
        private final String value;

        public Command(String value) {
            this.value = value;
            validateSelf();
        }
    }

    @Value
    @Builder
    @EqualsAndHashCode(callSuper = false)
    class Option extends SelfValidating<Option> {

        final boolean ignoreFailure;

        Option(boolean ignoreFailure) {
            this.ignoreFailure = ignoreFailure;
            this.validateSelf();
        }

    }



}
