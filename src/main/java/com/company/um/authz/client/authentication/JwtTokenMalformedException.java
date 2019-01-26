package com.company.um.authz.client.authentication;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
    public JwtTokenMalformedException(String message) {
        super(message);
    }
}
