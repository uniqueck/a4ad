package com.github.a4ad.adapter.rest.deployment;

import com.github.a4ad.common.RestAdapter;
import com.github.a4ad.port.in.deployment.StartDeploymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncWebRequest;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class StartDeploymentRestController {

    private final StartDeploymentUseCase startDeploymentUseCase;

    @PostMapping("/jobs/{jobName}/deployments")
    public AsyncWebRequest startDeployment() {
        return null;
    }

}
