package com.cpt.payments_processing_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handlerGenericError(Exception e) {
    log.error("Generic error occurred: ", e);
    ErrorResponse response = new ErrorResponse();
    response.setErrorCode(ErrorCodes.GENERIC_ERROR.getErrorCode());
    response.setErrorMessage(ErrorCodes.GENERIC_ERROR.getErrorMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(PaymentProcessingException.class)
  public ResponseEntity<ErrorResponse> handleProcessingException(PaymentProcessingException e) {
    log.error("Payment processing error occurred: ", e);
    ErrorResponse response = new ErrorResponse();
    response.setErrorCode(e.getErrorCode());
    response.setErrorMessage(e.getErrorMessage());
    return new ResponseEntity<>(response, e.getHttpStatus());
  }
}
