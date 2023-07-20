package com.univeristymanagement.api.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);

    }

    public ApiException() {
        super();
    }



}
