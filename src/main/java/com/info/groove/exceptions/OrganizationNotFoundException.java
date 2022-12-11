package com.info.groove.exceptions;

public class OrganizationNotFoundException extends RuntimeException {

    public OrganizationNotFoundException(String messageError) {
        super(messageError);
    }

}
