package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.constant.TransactionStatusEnum;
import com.cpt.payments_processing_service.dao.interfaces.TransactionDao;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.pojo.request.PaymentRequest;
import com.cpt.payments_processing_service.service.factory.PaymentFactoryPattern;
import com.cpt.payments_processing_service.service.interfaces.PaymentService;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Autowired private PaymentFactoryPattern factoryPattern;

    @Override
    @Transactional
    public String createPayment(PaymentRequestDTO requestDTO) {
        TransactionStatusEnum status = TransactionStatusEnum.getByName(requestDTO.getPaymentMode());
        PaymentStatusHandler paymentMode = factoryPattern.getStatusHandler(status);

        String response = paymentMode.processPayment(requestDTO);
        return "";
    }
}
