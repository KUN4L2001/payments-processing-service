package com.cpt.payments_processing_service.entity;

import lombok.Data;

@Data
public class PaymentRequestEntity {
  private String userId;
  private String paymentMethod;
  private String provider;
  private String paymentType;
  private String txnStatus;
  private String currency;
  private String amount;
  private String merchantTxnRef;
  private String txnReference;
}
