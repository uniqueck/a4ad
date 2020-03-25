package com.github.a4ad.domain.manage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobIdTest {

    @Test
    void sameValueAs() {
        assertTrue(new JobId(12L).sameValueAs(new JobId(12L)));
        assertFalse(new JobId(12L).sameValueAs(new JobId(11L)));
        assertFalse(new JobId(12L).sameValueAs(null));
    }

    @Test
    void testToString() {
        assertEquals("12", new JobId(12L).toString());
    }

    @Test void testHashCode() {
        assertEquals(new Long(12L).hashCode(), new JobId(12L).hashCode());
    }

}