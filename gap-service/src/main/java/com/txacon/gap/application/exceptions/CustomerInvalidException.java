package com.txacon.gap.application.exceptions;

public class CustomerInvalidException extends DefaultServiceException {

    public CustomerInvalidException(ApiError error) {
        super(error);
    }
}
