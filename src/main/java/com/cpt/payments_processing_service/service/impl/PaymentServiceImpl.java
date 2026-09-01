package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.service.factory.PaymentFactoryPattern;
import com.cpt.payments_processing_service.service.interfaces.PaymentService;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Autowired PaymentFactoryPattern factoryPattern;

    @Override
    public String createPayment(PaymentRequestDTO requestDTO) {
        PaymentStatusHandler paymentMode = factoryPattern.getStatusHandler(requestDTO.getPaymentMode());
        String response = paymentMode.processPayment(requestDTO);
        return "";
    }
}
