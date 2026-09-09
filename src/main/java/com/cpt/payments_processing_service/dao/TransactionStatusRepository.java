package com.cpt.payments_processing_service.dao;

import com.cpt.payments_processing_service.entity.TransactionStatusEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStatusRepository
    extends JpaRepository<TransactionStatusEntity, Integer> {
  Optional<TransactionStatusEntity> findByNameIgnoreCase(String name);
}
