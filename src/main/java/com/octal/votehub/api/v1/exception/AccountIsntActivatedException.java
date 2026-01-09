package com.octal.votehub.api.v1.exception;

public class AccountIsntActivatedException extends RuntimeException {
    public AccountIsntActivatedException(String message) {
        super(message);
    }
}
