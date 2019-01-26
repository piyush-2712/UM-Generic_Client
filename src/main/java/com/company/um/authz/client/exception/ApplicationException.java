package com.company.um.authz.client.exception;

import com.company.um.authz.client.responseWrapper.Status;

public class ApplicationException extends Exception {
	 
    private final Status status;
   

    public ApplicationException(Status status, String message) {
                    super(message);
                    this.status = status;  
    }

    public ApplicationException(Status status, Throwable t) {
                    super(t);
                    this.status = status;
    }

    public ApplicationException(Status status) {
                    super();
                    this.status = status;
    }
   
   

    /**
    * @return the status
    */
    public Status getStatus() {
                    return status;
    }
}
