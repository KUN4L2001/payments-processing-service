package com.cpt.payments_processing_service.service.interfaces;

import com.cpt.payments_processing_service.dto.request.InitiateRequestDTO;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.dto.response.InitiateResponseDTO;
import com.cpt.payments_processing_service.pojo.response.TransactionResponse;

public interface PaymentService {
  TransactionResponse createPayment(PaymentRequestDTO requestDTO);

    InitiateResponseDTO initiatePayment(InitiateRequestDTO requestDTO);
}
