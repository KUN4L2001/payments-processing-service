package com.cpt.payments_processing_service.service.interfaces;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;

public interface PaymentService {
    String createPayment(PaymentRequestDTO requestDTO);
}
