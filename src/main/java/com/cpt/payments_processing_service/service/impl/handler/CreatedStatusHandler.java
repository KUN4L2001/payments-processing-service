package com.cpt.payments_processing_service.service.impl.handler;

import com.cpt.payments_processing_service.dao.*;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.entity.*;
import com.cpt.payments_processing_service.pojo.response.TransactionResponse;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreatedStatusHandler extends PaymentStatusHandler {

  @Autowired private TransactionRepository transactionRepository;
  @Autowired private PaymentMethodRepository paymentMethodRepository;
  @Autowired private PaymentTypeRepository paymentTypeRepository;
  @Autowired private ProviderRepository providerRepository;
  @Autowired private TransactionStatusRepository transactionStatusRepository;
  @Autowired private ModelMapper modelMapper;

  @Override
  public TransactionResponse processPayment(PaymentRequestDTO requestDTO) {
    log.info("Inside CreatePayment {}", requestDTO);

    PaymentMethodEntity paymentMethod =
        paymentMethodRepository
            .findByNameIgnoreCase(requestDTO.getPaymentMethod())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Invalid payment method: " + requestDTO.getPaymentMethod()));

    PaymentTypeEntity paymentType =
        paymentTypeRepository
            .findByTypeIgnoreCase(requestDTO.getPaymentType())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Invalid payment type: " + requestDTO.getPaymentType()));

    ProviderEntity provider =
        providerRepository
            .findByProviderNameIgnoreCase(requestDTO.getProvider())
            .orElseThrow(
                () ->
                    new IllegalArgumentException("Invalid provider: " + requestDTO.getProvider()));

    TransactionStatusEntity status =
        transactionStatusRepository
            .findByNameIgnoreCase(requestDTO.getTxnStatus())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Invalid transaction status: " + requestDTO.getTxnStatus()));

    TransactionEntity transaction = new TransactionEntity();

    transaction.setUserId(Integer.valueOf(requestDTO.getUserId()));
    transaction.setPaymentMethod(paymentMethod);
    transaction.setPaymentType(paymentType);
    transaction.setProvider(provider);
    transaction.setTxnStatus(status);

    transaction.setAmount(new BigDecimal(requestDTO.getAmount()));

    transaction.setCurrency(requestDTO.getCurrency());
    transaction.setMerchantTransactionReference(requestDTO.getMerchantTxnRef());
    transaction.setTxnReference(requestDTO.getTxnReference());

    TransactionEntity savedTransaction = transactionRepository.save(transaction);
    return TransactionResponse.builder()
        .id(savedTransaction.getTxnId())
        .status(savedTransaction.getTxnStatus().getName())
        .build();
  }
}
