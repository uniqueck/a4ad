package com.github.a4ad.domain.manage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StageTest {

    @Test
    void sameValueAs() {
        Stage stage = new Stage("PROD");
        assertTrue(stage.sameValueAs(stage));
        assertTrue(stage.sameValueAs(new Stage("PROD")));
        assertFalse(stage.sameValueAs(new Stage("TEST")));
        assertFalse(stage.sameValueAs(null));
    }

    @Test
    void testHashCode() {
        assertEquals("TEST".hashCode(), new Stage("TEST").hashCode());
    }

    @Test
    void testEquals() {
        Stage stage = new Stage("PROD");
        assertEquals(stage, stage);
        assertEquals(stage, new Stage("PROD"));
        assertNotEquals(stage, null);
        assertNotEquals(stage, new Stage("TEST"));
    }

    @Test
    void testToString() {
        assertEquals("TEST", new Stage("TEST").toString());
    }

    @Test
    void getValue() {
        assertEquals("TEST", new Stage("TEST").getValue());
    }
}