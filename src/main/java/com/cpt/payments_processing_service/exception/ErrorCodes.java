package com.cpt.payments_processing_service.exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {
  GENERIC_ERROR("20001", "Something went wrong!"),
  INVALID_TXN_STATUS("20002", "Status cannot be null or empty"),
  NO_STATUS_FOUND("20003", "No enum found for status"),
  VALIDATION_ERROR("40001", "Invalid quantity");

  private String errorCode;
  private String errorMessage;

  ErrorCodes(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
