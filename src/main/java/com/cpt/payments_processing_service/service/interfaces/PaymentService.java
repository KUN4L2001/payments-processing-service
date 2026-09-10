package com.cpt.payments_processing_service.service.interfaces;

import com.cpt.payments_processing_service.dto.request.InitiateRequestDTO;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.dto.response.InitiateResponseDTO;
import com.cpt.payments_processing_service.dto.response.TransactionResponseDTO;

public interface PaymentService {
  TransactionResponseDTO createPayment(PaymentRequestDTO requestDTO);

  InitiateResponseDTO initiatePayment(InitiateRequestDTO requestDTO);
}
