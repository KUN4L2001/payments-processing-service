package com.cpt.payments_processing_service.service.interfaces;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.pojo.response.TransactionResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<TransactionResponse> createPayment(PaymentRequestDTO requestDTO);
}
