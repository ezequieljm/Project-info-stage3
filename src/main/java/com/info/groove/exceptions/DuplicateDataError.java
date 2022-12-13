package com.info.groove.exceptions;

public class DuplicateDataError extends RuntimeException{
    public DuplicateDataError(String messageError) {
        super(messageError);
    }
}
