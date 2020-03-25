package com.github.a4ad.domain.manage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Script")
class ScriptTest {

    @DisplayName("construct")
    @Test
    void testScript() {
        Script script = new Script("bin/startup.sh");
        assertEquals("bin/startup.sh", script.getValue());
    }

    @DisplayName("hashCode")
    @Test
    void testHashCode() {
        assertEquals("bin/startup.sh".hashCode(), new Script("bin/startup.sh").hashCode());
    }

    @DisplayName("toString")
    @Test
    void testToString() {
        assertEquals("bin/startup.sh", new Script("bin/startup.sh").toString());
    }

    @DisplayName("sameValueAs")
    @Test
    void testSameValueAs() {
        Script script = new Script("bin/startup.sh");
        assertTrue(script.sameValueAs(new Script("bin/startup.sh")));
        assertFalse(script.sameValueAs(new Script("bin/shutdown.sh")));
        assertFalse(script.sameValueAs(null));
    }

    @DisplayName("equals")
    @Test
    void testEquals() {
        Script script = new Script("bin/startup.sh");
        assertEquals(script, script);
        assertEquals(script, new Script("bin/startup.sh"));
        assertNotEquals(script, new Script("bin/shutdown.sh"));
        assertNotEquals(script, null);
    }

}