package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.constant.TransactionStatusEnum;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.pojo.response.TransactionResponse;
import com.cpt.payments_processing_service.service.factory.PaymentFactoryPattern;
import com.cpt.payments_processing_service.service.interfaces.PaymentService;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {

  @Autowired private PaymentFactoryPattern factoryPattern;

  @Override
  @Transactional
  public TransactionResponse createPayment(PaymentRequestDTO requestDTO) {
    TransactionStatusEnum status = TransactionStatusEnum.getByName(requestDTO.getTxnStatus());
    PaymentStatusHandler paymentStatus = factoryPattern.getStatusHandler(status);
    return paymentStatus.processPayment(requestDTO);
  }
}
