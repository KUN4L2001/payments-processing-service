package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.constant.TransactionStatusEnum;
import com.cpt.payments_processing_service.dto.request.InitiateRequestDTO;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.dto.response.InitiateResponseDTO;
import com.cpt.payments_processing_service.dto.response.TransactionResponseDTO;
import com.cpt.payments_processing_service.service.factory.PaymentFactoryPattern;
import com.cpt.payments_processing_service.service.interfaces.PaymentService;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import com.cpt.payments_processing_service.service.interfaces.RestService;
import jakarta.transaction.Transactional;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class PaymentServiceImpl implements PaymentService {

  @Autowired private PaymentFactoryPattern factoryPattern;
  @Autowired private RestService restService;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @Transactional
  public TransactionResponseDTO createPayment(PaymentRequestDTO requestDTO) {
    TransactionStatusEnum status = TransactionStatusEnum.getByName(requestDTO.getTxnStatus());
    PaymentStatusHandler paymentStatus = factoryPattern.getStatusHandler(status);
    return paymentStatus.processPayment(requestDTO);
  }

  @Override
  public InitiateResponseDTO initiatePayment(InitiateRequestDTO requestDTO) {
    Map<String, String> headers = Map.of("Content-Type", "application/json");

    String requestBody = objectMapper.writeValueAsString(requestDTO);
    ResponseEntity<String> response =
        restService.postRequest("http://localhost:8082/payment/create", requestBody, headers);

    InitiateResponseDTO responseDTO =
        objectMapper.readValue(response.getBody(), InitiateResponseDTO.class);
    return responseDTO;
  }
}
