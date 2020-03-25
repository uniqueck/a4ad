package com.github.a4ad.adapter.bpmn;

import com.github.a4ad.port.in.deployment.DeploymentStepCopyArtifactPort;
import com.github.a4ad.port.out.deployment.StartDeploymentProcessPort.DeploymentTriggerId;
import com.github.a4ad.port.out.deployment.StartDeploymentProcessPort.StartDeploymentProcessCommand;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StartDeploymentProcessFlowableAdapterTest {

    @TestConfiguration
    public static class Conf {
        @Bean
        DeploymentStepCopyArtifactPort copyArtifactToDeploymentDestinationPort() {
            return new DeploymentStepCopyArtifactPort() {

                @Override
                public boolean copyArtifactToDeploymentDestination(DeploymentStepCopyArtifactCommand command) {
                    assertEquals(20L, command.getDeploymentTriggerId().getValue());
                    return false;
                }
            };
        }
    }


    @Autowired
    RepositoryService repositoryService;

    @Autowired
    HistoryService historyService;

    @Autowired
    StartDeploymentProcessFlowableAdapter underTest;

    @DisplayName("check if process definition is deployed")
    @Test
    void testDeployment() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionId().asc().list();
        Assertions.assertNotNull(list);
        assertEquals(1, list.size());
        ProcessDefinition processDefinition = list.get(0);
        assertEquals("applicationContainerDeployment", processDefinition.getKey());
    }

    @DisplayName("start process and check if it in history exists")
    @Test
    void startDeploymentProcess() {
        String prozessInstanceId = underTest.startDeployment(new StartDeploymentProcessCommand(new DeploymentTriggerId(20L)));
        assertNotNull(prozessInstanceId);
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().processInstanceId(prozessInstanceId).list();
        assertEquals(1, list.size());
        assertEquals("applicationContainerDeployment", list.get(0).getProcessDefinitionKey());
    }


}