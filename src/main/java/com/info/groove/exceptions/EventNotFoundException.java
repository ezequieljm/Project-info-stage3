package com.info.groove.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String messageError) {
        super(messageError);
    }
}
