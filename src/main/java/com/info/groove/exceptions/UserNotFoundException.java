package com.info.groove.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String messageError) {
        super(messageError);
    }
}
