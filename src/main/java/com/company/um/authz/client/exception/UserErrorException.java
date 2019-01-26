package com.company.um.authz.client.exception;

import org.springframework.http.HttpStatus;

public class UserErrorException extends RuntimeException {
    private HttpStatus status;
    private String errorMessage;

    public UserErrorException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public UserErrorException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}