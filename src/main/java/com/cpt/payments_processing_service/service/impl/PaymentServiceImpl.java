package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.constant.TransactionStatusEnum;
import com.cpt.payments_processing_service.dao.TransactionLogRepository;
import com.cpt.payments_processing_service.dao.TransactionRepository;
import com.cpt.payments_processing_service.dao.TransactionStatusRepository;
import com.cpt.payments_processing_service.dto.request.InitiateRequestDTO;
import com.cpt.payments_processing_service.dto.request.PaymentRequestDTO;
import com.cpt.payments_processing_service.dto.response.InitiateResponseDTO;
import com.cpt.payments_processing_service.dto.response.TransactionResponseDTO;
import com.cpt.payments_processing_service.entity.TransactionEntity;
import com.cpt.payments_processing_service.entity.TransactionLogEntity;
import com.cpt.payments_processing_service.entity.TransactionStatusEntity;
import com.cpt.payments_processing_service.exception.ErrorCodes;
import com.cpt.payments_processing_service.exception.ErrorResponse;
import com.cpt.payments_processing_service.exception.PaymentProcessingException;
import com.cpt.payments_processing_service.service.factory.PaymentFactoryPattern;
import com.cpt.payments_processing_service.service.interfaces.PaymentService;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import com.cpt.payments_processing_service.service.interfaces.RestService;
import jakarta.transaction.Transactional;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class PaymentServiceImpl implements PaymentService {

  @Autowired private PaymentFactoryPattern factoryPattern;
  @Autowired private RestService restService;
  @Autowired private ObjectMapper objectMapper;

  @Autowired private TransactionRepository transactionRepository;

  @Autowired private TransactionStatusRepository transactionStatusRepository;

  @Autowired private TransactionLogRepository transactionLogRepository;

  @Override
  @Transactional
  public TransactionResponseDTO createPayment(PaymentRequestDTO requestDTO) {
    if (requestDTO.getTxnStatus() == null) {
      throw new PaymentProcessingException(
          ErrorCodes.INVALID_TXN_STATUS.getErrorCode(),
          ErrorCodes.INVALID_TXN_STATUS.getErrorMessage(),
          HttpStatus.BAD_REQUEST);
    }
    TransactionStatusEnum status = TransactionStatusEnum.getByName(requestDTO.getTxnStatus());
    PaymentStatusHandler paymentStatus = factoryPattern.getStatusHandler(status);
    if (paymentStatus == null) {
      throw new PaymentProcessingException(
          ErrorCodes.NO_STATUS_FOUND.getErrorCode(),
          ErrorCodes.NO_STATUS_FOUND.getErrorMessage(),
          HttpStatus.BAD_REQUEST);
    }
    return paymentStatus.processPayment(requestDTO);
  }

  @Override
  public InitiateResponseDTO initiatePayment(InitiateRequestDTO requestDTO) {
    TransactionEntity transaction =
        transactionRepository
            .findById(requestDTO.getTxnRef())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Transaction not found: " + requestDTO.getTxnRef()));
    updateStatus(transaction, "INITIATED", "");
    Map<String, String> headers = Map.of("Content-Type", "application/json");

    String requestBody = objectMapper.writeValueAsString(requestDTO);
    ResponseEntity<String> response =
        restService.postRequest("http://localhost:8082/payment/create", requestBody, headers);

    InitiateResponseDTO responseDTO = processResponse(response);

    updateStatus(transaction, "PENDING", responseDTO.getId());
    return responseDTO;
  }

  private InitiateResponseDTO processResponse(ResponseEntity<String> response) {
    if (response.getStatusCode() == HttpStatus.CREATED) {
      return objectMapper.readValue(response.getBody(), InitiateResponseDTO.class);
    } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
      throw new PaymentProcessingException(
          ErrorCodes.GENERIC_ERROR.getErrorCode(),
          ErrorCodes.GENERIC_ERROR.getErrorMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      ErrorResponse errorResponse = objectMapper.readValue(response.getBody(), ErrorResponse.class);
      throw new PaymentProcessingException(
          errorResponse.getErrorCode(),
          errorResponse.getErrorMessage(),
          HttpStatus.valueOf(response.getStatusCode().value()));
    }
  }

  @Transactional
  public void updateStatus(TransactionEntity transaction, String newStatusName, String txnRef) {

    String oldStatus = transaction.getTxnStatus().getName();

    TransactionStatusEntity newStatus =
        transactionStatusRepository
            .findByNameIgnoreCase(newStatusName)
            .orElseThrow(
                () -> new IllegalArgumentException("Invalid transaction status: " + newStatusName));

    transaction.setTxnStatus(newStatus);
    transaction.setProviderReference(txnRef);

    transactionRepository.save(transaction);

    TransactionLogEntity transactionLog = new TransactionLogEntity();

    transactionLog.setTransaction(transaction);
    transactionLog.setTxnFromStatus(oldStatus);
    transactionLog.setTxnToStatus(newStatus.getName());

    transactionLogRepository.save(transactionLog);
  }
}
