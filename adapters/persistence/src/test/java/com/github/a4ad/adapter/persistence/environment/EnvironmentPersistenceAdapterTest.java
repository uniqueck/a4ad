package com.github.a4ad.adapter.persistence.environment;

import com.github.a4ad.port.out.LoadEnvironmentPort;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import({EnvironmentPersistenceAdapter.class})
class EnvironmentPersistenceAdapterTest {

    @Autowired
    private EnvironmentPersistenceAdapter underTest;

    @Test
    @Sql("EnvironmentPersistenceAdapterTest.sql")
    void loadEnvironment() {
        LoadEnvironmentPort.EnvironmentPortModel environmentPortModel = underTest.loadEnvironment(new EnvironmentPortAdapterModel.EnvironmentIdPortAdapterModel(1L));

        assertNotNull(environmentPortModel);
        assertNotNull(environmentPortModel.getId());
        assertEquals((Long)1L, environmentPortModel.getId().getValue());
    }
}