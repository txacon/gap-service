package com.txacon.gap.application.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DefaultServiceException extends RuntimeException {

  protected ApiError error;

  public String getExceptionMessage() {
    return error != null ? error.getMessageError() : "";
  }
}
