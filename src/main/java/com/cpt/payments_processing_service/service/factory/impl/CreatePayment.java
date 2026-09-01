package com.cpt.payments_processing_service.service.factory.impl;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.service.factory.PaymentMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreatePayment extends PaymentMode {

    @Override
    public String payment(PaymentRequestDTO requestDTO) {
        log.info("Inside CreatePayment {}",requestDTO);
        return "";
    }
}
