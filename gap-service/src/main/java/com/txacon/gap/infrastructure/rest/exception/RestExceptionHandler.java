package com.txacon.gap.infrastructure.rest.exception;

import com.txacon.gap.application.exceptions.BusinessInvalidException;
import com.txacon.gap.application.exceptions.BusinessNotFoundException;
import com.txacon.gap.application.exceptions.CustomerInvalidException;
import com.txacon.gap.application.exceptions.CustomerNotFoundException;
import com.txacon.gap.application.exceptions.DefaultServiceException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(
      DataIntegrityViolationException ex, HttpServletRequest request) {
    return new ResponseEntity<>(
        handleExceptionResponse(ex.getMostSpecificCause(), request), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({CustomerNotFoundException.class, BusinessNotFoundException.class})
  public ResponseEntity<Object> handleNotFound(
      DefaultServiceException ex, HttpServletRequest request) {
    return new ResponseEntity<>(handleExceptionResponse(ex, request), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({CustomerInvalidException.class, BusinessInvalidException.class})
  public ResponseEntity<Object> handleInvalidRequest(
      DefaultServiceException ex, HttpServletRequest request) {
    return new ResponseEntity<>(handleExceptionResponse(ex, request), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraint(
      ConstraintViolationException ex, HttpServletRequest request) {
    return new ResponseEntity<>(handleExceptionResponse(ex, request), HttpStatus.BAD_REQUEST);
  }

  private Map<String, Object> handleExceptionResponse(
      Throwable exception, HttpServletRequest request) {
    return getStringObjectMap(
        Collections.singleton(exception.getMessage()),
        request.getMethod(),
        request.getRequestURI());
  }

  public Map<String, Object> getStringObjectMap(
      Collection<String> messages, String method, String endpoint) {
    Map<java.lang.String, java.lang.Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("messages", messages);
    body.put("method", method);
    body.put("endpoint", endpoint);
    return body;
  }
}
