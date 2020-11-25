package com.fernando.workshopmongo.services.exceptions;

import com.fernando.workshopmongo.resources.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  public static final String OBJECT_NOT_FOUND_MESSAGE = "Object not found";

  @ExceptionHandler(ObjectNotFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ResponseEntity<StandardError> objectNotFound(
      ObjectNotFoundException e, HttpServletRequest request) {
    final HttpStatus status = HttpStatus.NOT_FOUND;
    return ResponseEntity.status(status)
        .body(
            StandardError.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.value())
                .message(OBJECT_NOT_FOUND_MESSAGE)
                .error(e.getMessage())
                .path(request.getRequestURI())
                .build());
  }
}
