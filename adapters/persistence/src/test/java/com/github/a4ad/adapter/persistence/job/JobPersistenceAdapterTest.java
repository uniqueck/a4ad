package com.github.a4ad.adapter.persistence.job;

import com.github.a4ad.port.out.LoadJobPort;
import lombok.Value;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import({JobPersistenceAdapter.class, JobMapper.class})
class JobPersistenceAdapterTest {

    @Autowired
    private JobPersistenceAdapter underTest;

    @Autowired
    private JobRepository jobRepository;


    @Test
    @Sql("JobPersistenceAdapterTest.sql")
    void loadJob() {

        LoadJobPort.JobPortModel jobPortModel = underTest.loadJob(new JobPortTransferModel.JobIdPortTransferModel(1L));

        assertNotNull(jobPortModel);
        assertNotNull(jobPortModel.getId());
        assertEquals((Long)1L, jobPortModel.getId().getValue());
    }
}