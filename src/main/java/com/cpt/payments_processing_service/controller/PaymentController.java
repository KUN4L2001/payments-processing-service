package com.cpt.payments_processing_service.controller;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.pojo.request.PaymentRequest;
import com.cpt.payments_processing_service.pojo.response.GenericRequest;
import com.cpt.payments_processing_service.pojo.response.TransactionResponse;
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
  public ResponseEntity<TransactionResponse> createPayment(
      @RequestBody GenericRequest<PaymentRequest> request) {
    PaymentRequestDTO requestDTO = modelMapper.map(request.getData(), PaymentRequestDTO.class);
    TransactionResponse response = paymentService.createPayment(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
