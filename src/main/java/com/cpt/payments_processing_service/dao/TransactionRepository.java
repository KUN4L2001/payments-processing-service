package com.cpt.payments_processing_service.dao;

import com.cpt.payments_processing_service.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {}
