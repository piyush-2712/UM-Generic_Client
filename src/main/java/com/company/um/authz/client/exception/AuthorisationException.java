package com.company.um.authz.client.exception;

/**
 * To be thrown by child classes of BasePermission for sending authorization error message to user.
 * Only set message which can be shown to API/Service user, do not set debug messages here.
 *
 */
public class AuthorisationException extends Exception {

  public AuthorisationException(String message) {
    super(message);
  }
}