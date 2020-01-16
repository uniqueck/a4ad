package com.github.a4ad.adapter.bpmn;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.test.Deployment;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.impl.test.FlowableSpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(FlowableSpringExtension.class)
@ExtendWith(SpringExtension.class)
class BpmnAdapterTest {


    @AutoConfigureDataJdbc
    @Configuration(proxyBeanMethods = false)
    public static class TestConf {

        @Bean
        public ProcessEngineConfiguration processEngineConfiguration() {
            SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
            return springProcessEngineConfiguration;
        }

        @Bean
        public ProcessEngine processEngine(ProcessEngineConfiguration cfg) {
            return cfg.buildProcessEngine();
        }

    }

    @Test
    @Deployment(resources = {"processes/example-bpmn.xml"})
    void test() {

    }


}