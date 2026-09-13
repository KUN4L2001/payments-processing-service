package com.cpt.payments_processing_service.exception;

import lombok.Data;

@Data
public class ErrorResponse {
  private String errorCode;
  private String errorMessage;
}
