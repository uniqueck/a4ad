package com.github.a4ad.domain.manage.server;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class Authentication {

    public static Authentication withPassword(AuthUser user, AuthSecret secret) {
        return new Authentication(user, AuthType.PASSWORD, secret);
    }

    public static Authentication withKey(AuthUser user, AuthSecret secret) {
        return new Authentication(user, AuthType.KEY, secret);
    }

    AuthUser authUser;
    AuthType type;
    AuthSecret authsecret;

    @Value
    public static class AuthUser {
        String value;
    }

    @Value
    public static class AuthSecret {
        String value;
    }

    public static enum AuthType {
        PASSWORD, KEY
    }


}
