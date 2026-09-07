package com.cpt.payments_processing_service.service.impl.handler;

import com.cpt.payments_processing_service.dao.interfaces.TransactionDao;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.entity.PaymentRequestEntity;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreatedStatusHandler extends PaymentStatusHandler {

    @Autowired private TransactionDao transactionDao;
    @Autowired private ModelMapper modelMapper;

    @Override
    public String processPayment(PaymentRequestDTO requestDTO) {
        log.info("Inside CreatePayment {}",requestDTO);
        PaymentRequestEntity request = modelMapper.map(requestDTO, PaymentRequestEntity.class);
        String res = transactionDao.createTransaction(request);
        return res;
    }
}
