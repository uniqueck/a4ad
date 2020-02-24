package com.github.a4ad.adapter.web;

import com.github.a4ad.common.RestAdapter;
import com.github.a4ad.port.in.pushartifact.PushArtifactUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RestAdapter
class PushArtifactController {

    final PushArtifactUseCase pushArtifactUseCase;

    @PostMapping("/jobs/{jobName}/artifacts")
    @ResponseStatus(HttpStatus.CREATED)
    public void pushArtifact(@PathVariable("jobName") String jobName, @RequestParam("file") MultipartFile multipartFile) {
        try {
            pushArtifactUseCase.saveArtifact(new PushArtifactUseCase.PushArtifactUseCaseCommand(jobName, multipartFile.getOriginalFilename(), multipartFile.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Upload des Artifacts",e);
        }
    }


}

