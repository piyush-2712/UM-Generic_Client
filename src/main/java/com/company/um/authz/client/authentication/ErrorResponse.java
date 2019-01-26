package com.company.um.authz.client.authentication;

import java.util.List;


public class ErrorResponse {
    private String message;
    private int code;
    private List<ParameterError> errorParameters;

    public ErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public List<ParameterError> getErrorParameters() {
        return errorParameters;
    }

    public void setErrorParameters(List<ParameterError> errorParameters) {
        this.errorParameters = errorParameters;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
