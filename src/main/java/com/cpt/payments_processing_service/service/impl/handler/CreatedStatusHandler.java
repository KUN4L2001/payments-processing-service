package com.cpt.payments_processing_service.service.impl.handler;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreatedStatusHandler extends PaymentStatusHandler {

    @Override
    public String processPayment(PaymentRequestDTO requestDTO) {
        log.info("Inside CreatePayment {}",requestDTO);
        return "";
    }
}
