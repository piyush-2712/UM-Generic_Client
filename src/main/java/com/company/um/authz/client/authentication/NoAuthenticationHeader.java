package com.company.um.authz.client.authentication;

import org.springframework.security.core.AuthenticationException;


public class NoAuthenticationHeader extends AuthenticationException {

  public NoAuthenticationHeader(String msg) {
    super(msg);
  }
}
