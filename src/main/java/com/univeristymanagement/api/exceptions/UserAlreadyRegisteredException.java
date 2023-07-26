package com.univeristymanagement.api.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String email ) {
        super(String.format("user with email: %s  is already registered", email ));

    }
}
