package com.txacon.gap.application.exceptions;

public class BusinessNotFoundException extends DefaultServiceException {

  public BusinessNotFoundException(ApiError error) {
    super(error);
  }
}
