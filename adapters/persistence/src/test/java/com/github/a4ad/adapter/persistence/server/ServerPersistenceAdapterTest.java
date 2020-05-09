package com.github.a4ad.adapter.persistence.server;

import com.github.a4ad.adapter.persistence.repositories.ServerJpaRepository;
import com.github.a4ad.port.out.persistence.ListServersPort;
import com.github.a4ad.port.out.persistence.LoadServerPort;
import com.github.a4ad.port.out.persistence.SaveServerPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({ServerPersistenceAdapter.class})
class ServerPersistenceAdapterTest {

    @Autowired
    private ServerPersistenceAdapter underTest;

    @Autowired
    private ServerJpaRepository serverJpaRepository;

    @Autowired
    EntityManager em;

    @Test
    @Sql("ServerPersistenceAdapterTest.sql")
    void loadServers() {

        List<ListServersPort.ListServersPortModel> servers = underTest.loadServers();

        assertNotNull(servers);
        assertFalse(servers.isEmpty());
        assertEquals(1, servers.size());
        assertEquals(new ListServersPort.ListServersPortModel("server", "ip", 22), servers.get(0));
    }

    @Test
    @Sql("ServerPersistenceAdapterTest.sql")
    void deleteServer() {

        assertEquals(1, serverJpaRepository.count());
        underTest.deleteServer("server");
        assertEquals(0, serverJpaRepository.count());
    }

    @Test
    @Sql("ServerPersistenceAdapterTest.sql")
    void loadServerByName() {

        LoadServerPort.LoadServerPortModel server = underTest.loadServerByName("server");
        assertEquals(new LoadServerPort.LoadServerPortModel("server", "ip", 22), server);
        server = underTest.loadServerByName("nicht vorhanden");
        assertNull(server);
    }

    @Test
    void saveServer() {
        underTest.saveServer(new SaveServerPort.SaveServerPortModel("server","ip", 22));
        assertEquals(1, serverJpaRepository.count());
    }

}