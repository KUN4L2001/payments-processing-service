package com.cpt.payments_processing_service.service.recon;

import com.cpt.payments_processing_service.dao.TransactionRepository;
import com.cpt.payments_processing_service.dao.TransactionStatusRepository;
import com.cpt.payments_processing_service.dto.event.MailEvent;
import com.cpt.payments_processing_service.dto.response.RetrievePaymentResponseDTO;
import com.cpt.payments_processing_service.entity.TransactionEntity;
import com.cpt.payments_processing_service.entity.TransactionStatusEntity;
import com.cpt.payments_processing_service.service.interfaces.KafkaProducerService;
import com.cpt.payments_processing_service.service.interfaces.RestService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
public class ReconTransactionAsync {

  @Autowired private RestService restService;
  @Autowired private TransactionStatusRepository transactionStatusRepository;
  @Autowired private TransactionRepository transactionRepository;
  @Autowired private KafkaProducerService kafkaProducerService;
  @Autowired private ObjectMapper objectMapper;

  @Async("taskExecutor")
  public void task(TransactionEntity transactionEntity) {
    Map<String, String> headers = Map.of("Content-Type", "application/json");
    String expireUrl = "http://localhost:8082/payment/expire";
    Integer count = transactionEntity.getRetryCount();
    if (count < 3) {
      // TODO: Send notification
      String url =
          "http://localhost:8082/payment/retrieve/" + transactionEntity.getProviderReference();
      ResponseEntity<String> retrieveResponse = restService.getRequest(url, headers);
      RetrievePaymentResponseDTO responseDTO =
          objectMapper.readValue(retrieveResponse.getBody(), RetrievePaymentResponseDTO.class);

      MailEvent mailEvent = new MailEvent();
      mailEvent.setTransactionId(transactionEntity.getTxnId());
      mailEvent.setEmail("kunalgaikwad0257@gmail.com");
      mailEvent.setSubject("Payment Link");
      mailEvent.setMessage("Please complete your payment using the link below.");
      mailEvent.setLink(responseDTO.getUrl());
      log.info("Url: {}", responseDTO.getUrl());
      log.info("Notification sent");
      kafkaProducerService.sendMailEvent(mailEvent);
      transactionEntity.setRetryCount(transactionEntity.getRetryCount() + 1);
    } else {
      expireUrl = expireUrl + "/" + transactionEntity.getProviderReference();
      ResponseEntity<String> response = restService.postRequest(expireUrl, null, headers);
      if (response.getStatusCode().is2xxSuccessful()) {
        TransactionStatusEntity transactionStatusEntity =
            transactionStatusRepository.getByStatus("5");
        transactionEntity.setTxnStatus(transactionStatusEntity);
        transactionRepository.save(transactionEntity);
        log.info("Transaction {} expired successfully", transactionEntity.getTxnId());
      }
    }
    transactionRepository.save(transactionEntity);
    log.info("Task executed");
  }
}
