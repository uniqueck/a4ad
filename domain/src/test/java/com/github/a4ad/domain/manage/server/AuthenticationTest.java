package com.github.a4ad.domain.manage.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTest {

    @Test
    void withPassword() {
        Authentication authentication = Authentication.withPassword(new Authentication.AuthUser("user1"), new Authentication.AuthSecret("password"));
        assertNotNull(authentication);
        assertEquals("user1", authentication.getAuthUser().getValue());
        assertEquals("password", authentication.getAuthsecret().getValue());
        assertEquals(Authentication.AuthType.PASSWORD, authentication.getType());
    }

    @Test
    void withKey() {
        Authentication authentication = Authentication.withKey(new Authentication.AuthUser("user1"), new Authentication.AuthSecret("longKey"));
        assertNotNull(authentication);
        assertEquals("user1", authentication.getAuthUser().getValue());
        assertEquals("longKey", authentication.getAuthsecret().getValue());
        assertEquals(Authentication.AuthType.KEY, authentication.getType());
    }
}