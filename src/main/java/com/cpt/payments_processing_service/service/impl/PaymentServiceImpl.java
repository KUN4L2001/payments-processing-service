package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.service.PaymentService;
import com.cpt.payments_processing_service.service.factory.PaymentMode;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String createPayment(PaymentRequestDTO requestDTO) {
        PaymentMode paymentMode = PaymentMode.getMode(requestDTO.getPaymentMode());
        String response = paymentMode.payment(requestDTO);
        return "";
    }
}
