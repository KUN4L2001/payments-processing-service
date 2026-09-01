package com.cpt.payments_processing_service.controller;

import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.pojo.request.PaymentRequest;
import com.cpt.payments_processing_service.service.interfaces.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired private PaymentService paymentService;
    @Autowired private ModelMapper modelMapper;

    @PostMapping("/create")
    public String createPayment(@RequestBody PaymentRequest request){
        PaymentRequestDTO requestDTO = modelMapper.map(request, PaymentRequestDTO.class);
        return paymentService.createPayment(requestDTO);
    }
}
