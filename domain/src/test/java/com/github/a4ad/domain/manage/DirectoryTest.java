package com.github.a4ad.domain.manage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Directory")
class DirectoryTest {


    @DisplayName("construct")
    @Test
    void directory() {
        assertEquals("/", new Directory("").getValue());
        assertEquals("/", new Directory("  ").getValue());
        assertEquals("/", new Directory("/").getValue());
        assertEquals("/", new Directory("/ ").getValue());
        assertEquals("/", new Directory(" / ").getValue());
        assertEquals("/", new Directory(" /").getValue());
        assertEquals("bin/", new Directory("bin").getValue());
        assertEquals("bin/", new Directory("bin/").getValue());
        assertEquals("/bin/", new Directory("/bin/").getValue());
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Directory(null));
        assertEquals("value is null", illegalArgumentException.getMessage());
    }

    @DisplayName("absolute")
    @Test
    void absolute() {
        Directory directoryWithoutEndingSlash = new Directory("/dir");
        Directory directoryWithEndingSlash = new Directory("/dir/");
        assertEquals(new Directory("/dir/dir/"), directoryWithoutEndingSlash.absolute(directoryWithEndingSlash));
        assertEquals(new Directory("/dir/dir"), directoryWithoutEndingSlash.absolute(directoryWithoutEndingSlash));
        assertEquals(new Directory("/dir/dir/"), directoryWithEndingSlash.absolute(directoryWithEndingSlash));
        assertEquals(new Directory("/dir/bin"), directoryWithEndingSlash.absolute(new Directory("bin")));
        assertEquals(new Directory("/dir/bin"), directoryWithoutEndingSlash.absolute(new Directory("bin")));
    }

    @Test
    @DisplayName("toString")
    void testToString() {
        assertEquals("/bin/", new Directory("/bin").toString());
    }

    @Test
    @DisplayName("hashCode")
    void testHashCode() {
        assertEquals("/bin/".hashCode(), new Directory("/bin").hashCode());
    }

}