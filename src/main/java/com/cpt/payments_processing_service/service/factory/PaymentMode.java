package com.cpt.payments_processing_service.service.factory;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.service.factory.impl.CreatePayment;

public abstract class PaymentMode {
    public abstract String payment(PaymentRequestDTO requestDTO);

    public static PaymentMode getMode(String mode){
        if (mode.equalsIgnoreCase("CREATED")) {
            return new CreatePayment();
        } else {
            return null;
        }
    }
}
