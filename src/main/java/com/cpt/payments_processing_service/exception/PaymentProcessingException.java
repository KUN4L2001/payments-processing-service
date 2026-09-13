package com.cpt.payments_processing_service.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class PaymentProcessingException extends RuntimeException {
  private final String errorCode;
  private final String errorMessage;
  private final HttpStatus httpStatus;
}
