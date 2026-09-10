package com.cpt.payments_processing_service.service.interfaces;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.dto.response.TransactionResponseDTO;

public abstract class PaymentStatusHandler {
  public abstract TransactionResponseDTO processPayment(PaymentRequestDTO requestDTO);
}
