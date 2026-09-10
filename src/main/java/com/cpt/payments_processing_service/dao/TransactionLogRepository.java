package com.cpt.payments_processing_service.dao;

import com.cpt.payments_processing_service.entity.TransactionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLogRepository extends JpaRepository<TransactionLogEntity, String> {}
