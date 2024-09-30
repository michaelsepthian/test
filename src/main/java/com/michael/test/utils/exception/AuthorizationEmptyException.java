package com.michael.test.utils.exception;

public class AuthorizationEmptyException extends RuntimeException {
    public AuthorizationEmptyException(String message) {
        super(message);
    }
}
