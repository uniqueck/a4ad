package com.github.a4ad.port.in.pushartifact;

import com.github.a4ad.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface PushArtifactUseCase {

    @Value
    @EqualsAndHashCode(callSuper = false)
    class PushArtifactUseCaseCommand extends SelfValidating<PushArtifactUseCaseCommand> {

        @NotBlank
        final String jobName;
        @NotBlank
        final String fileName;
        @NotNull
        final byte[] content;

        public PushArtifactUseCaseCommand(String jobName, String fileName, byte[] content) {
            this.jobName = jobName;
            this.fileName = StringUtils.cleanPath(fileName);
            this.content = content.clone();
            validateSelf();
        }


    }

    void saveArtifact(PushArtifactUseCaseCommand command);

}
