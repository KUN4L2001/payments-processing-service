package com.cpt.payments_processing_service.controller;

import com.cpt.payments_processing_service.dto.request.InitiateRequestDTO;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.dto.response.InitiateResponseDTO;
import com.cpt.payments_processing_service.dto.response.TransactionResponseDTO;
import com.cpt.payments_processing_service.pojo.response.GenericRequest;
import com.cpt.payments_processing_service.service.interfaces.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

  @Autowired private PaymentService paymentService;
  @Autowired private ModelMapper modelMapper;

  @PostMapping("/create")
  public ResponseEntity<TransactionResponseDTO> createPayment(
      @RequestBody GenericRequest<PaymentRequestDTO> request) {
    TransactionResponseDTO response = paymentService.createPayment(request.getData());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/initiate")
  public ResponseEntity<InitiateResponseDTO> initiatePayment(
      @RequestBody GenericRequest<InitiateRequestDTO> request) {
    InitiateResponseDTO response = paymentService.initiatePayment(request.getData());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
