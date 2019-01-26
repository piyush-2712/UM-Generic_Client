package com.company.um.authz.client.authentication;


public class ParameterError {
    private String parameterName;
    private String errorMessage;
    private int code;

    public ParameterError(String parameterName, String errorMessage) {
        this.parameterName = parameterName;
        this.errorMessage = errorMessage;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
