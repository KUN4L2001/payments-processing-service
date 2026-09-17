package com.cpt.payments_processing_service.dao;

import com.cpt.payments_processing_service.entity.TransactionEntity;
import com.cpt.payments_processing_service.entity.TransactionStatusEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
  TransactionEntity getByProviderReference(String id);

  List<TransactionEntity> getByTxnStatus(TransactionStatusEntity transactionStatusEntity);
}
