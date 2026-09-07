package com.cpt.payments_processing_service.entity;

import lombok.Data;

@Data
public class PaymentRequestEntity {
    private String userId;
    private String paymentMode;
    private String provider;
    private String currency;
    private String amount;
}
