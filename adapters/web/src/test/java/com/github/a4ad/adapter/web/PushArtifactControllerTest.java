package com.github.a4ad.adapter.web;

import com.github.a4ad.port.in.pushartifact.PushArtifactUseCase;
import lombok.Value;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PushArtifactController.class)
class PushArtifactControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PushArtifactUseCase pushArtifactUseCase;


    @Test
    void pushArtifcat() throws Exception {

        MockMultipartFile multipartFile = new MockMultipartFile("file", "originalName.txt","application/zip","content".getBytes(StandardCharsets.UTF_8));
        mockMvc.perform(multipart("/jobs/{jobName}/artifacts", "jobName").file(multipartFile).characterEncoding("UTF_8")).andExpect(status().isCreated());

        then(pushArtifactUseCase).should().saveArtifact(new PushArtifactUseCase.PushArtifactUseCaseCommand("jobName", multipartFile.getOriginalFilename(), multipartFile.getBytes()));

    }

}