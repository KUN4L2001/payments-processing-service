package com.cpt.payments_processing_service.service.interfaces;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;

public abstract class PaymentStatusHandler {
    public abstract String processPayment(PaymentRequestDTO requestDTO);
}
