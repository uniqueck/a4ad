package com.github.a4ad.domain.manage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobNameTest {

    @Test
    void sameIdentityAs() {
        assertTrue(new JobName("jobName").sameIdentityAs(new JobName("jobName")));
        assertFalse(new JobName("jobName").sameIdentityAs(new JobName("jobName2")));
        assertFalse(new JobName("jobName").sameIdentityAs(null));
    }

    @Test
    void testHashCode() {
        assertEquals("JobName".hashCode(), new JobName("JobName").hashCode());
    }

    @Test
    void testToString() {
        assertEquals("JobName", new JobName("JobName").toString());
    }

    @Test
    void getValue() {
        assertEquals("JobName", new JobName("JobName").getValue());
    }

    @Test
    void testEquals() {
        JobName jobName = new JobName("JobName");
        assertEquals(jobName, new JobName("JobName"));
        assertEquals(new JobName("JobName"), new JobName("JobName"));
        assertNotEquals(new JobName("JobName"), new JobName("JobName1"));
        assertNotEquals(new JobName("JobName"), null);
    }
}