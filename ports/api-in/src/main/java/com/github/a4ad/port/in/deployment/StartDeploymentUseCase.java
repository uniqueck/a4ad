package com.github.a4ad.port.in.deployment;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

public interface StartDeploymentUseCase {

    @Value
    @EqualsAndHashCode(callSuper = false)
    class StartDeploymentUseCaseCommand extends SelfValidating<StartDeploymentUseCaseCommand> {

        @NotBlank
        String jobName;
        @NotBlank
        String fileName;
        @NotNull
        byte[] content;

        public StartDeploymentUseCaseCommand(String jobName, String fileName, byte[] content) {
            this.jobName = jobName;
            this.fileName = StringUtils.cleanPath(fileName);
            this.content = content.clone();
            validateSelf();
        }

    }

    CompletableFuture<Object> startDeployment(StartDeploymentUseCaseCommand command);

}
