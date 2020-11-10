package com.txacon.gap.application.exceptions;

public class CustomerNotFoundException extends DefaultServiceException {

    public CustomerNotFoundException(ApiError error) {
        super(error);
    }
}
