package com.cpt.payments_processing_service.pojo.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private String userId;
    private String paymentMode;
    private String provider;
    private String currency;
    private String amount;
    private String txnStatus;
}
