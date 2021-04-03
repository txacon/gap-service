package com.txacon.gap.application.exceptions;

public class CustomerAlreadyExistsException extends DefaultServiceException {

  public CustomerAlreadyExistsException(ApiError error) {
    super(error);
  }
}
