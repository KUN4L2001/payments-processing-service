package com.cpt.payments_processing_service.dto.request;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private String userId;
    private String paymentMode;
    private String provider;
    private String currency;
    private String amount;
}