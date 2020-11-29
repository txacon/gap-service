package com.txacon.gap.application.exceptions;

public class BusinessInvalidException extends DefaultServiceException {
    public BusinessInvalidException(ApiError error) {
        super(error);
    }
}
