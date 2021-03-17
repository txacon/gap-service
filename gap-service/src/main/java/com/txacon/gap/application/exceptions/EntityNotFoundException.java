package com.txacon.gap.application.exceptions;

public class EntityNotFoundException extends DefaultServiceException {

  public EntityNotFoundException(ApiError error) {
    super(error);
  }
}
