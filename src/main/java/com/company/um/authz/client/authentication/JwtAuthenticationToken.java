package com.company.um.authz.client.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String authToken;

    public JwtAuthenticationToken(String authToken) {
        super(null, null);
        this.authToken = authToken;
    }

    public String getToken() {
        return authToken;
    }
}
