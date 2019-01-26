package com.company.um.authz.client.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.company.um.authz.client.exception.ApplicationException;
import com.company.um.authz.client.model.AuthenticatedUser;
import com.company.um.authz.client.responseWrapper.Status;


@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private AuthenticateTokenService authenticateTokenService;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();
        AuthenticatedUser parsedUser;
		try {
			parsedUser = authenticateTokenService.parseToken(token);
		} catch (ApplicationException e) {
			throw new JwtTokenMalformedException(e.getMessage());
		}

        if (parsedUser == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }
        return parsedUser;
    }

}
