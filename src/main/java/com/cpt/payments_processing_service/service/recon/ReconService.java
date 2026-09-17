package com.cpt.payments_processing_service.service.recon;

import com.cpt.payments_processing_service.dao.TransactionRepository;
import com.cpt.payments_processing_service.dao.TransactionStatusRepository;
import com.cpt.payments_processing_service.entity.TransactionEntity;
import com.cpt.payments_processing_service.entity.TransactionStatusEntity;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReconService {

  @Autowired private ReconTransactionAsync reconTransactionAsync;
  @Autowired private TransactionStatusRepository transactionStatusRepository;
  @Autowired private TransactionRepository transactionRepository;

  @Scheduled(cron = "1/10 * * * * ?")
  private void performTask() {
    TransactionStatusEntity transactionStatusEntity = transactionStatusRepository.getByStatus("3");
    List<TransactionEntity> list = transactionRepository.getByTxnStatus(transactionStatusEntity);
    log.info("Task executed");
    for (TransactionEntity transactionEntity : list) {
      reconTransactionAsync.task(transactionEntity);
    }
  }
}
