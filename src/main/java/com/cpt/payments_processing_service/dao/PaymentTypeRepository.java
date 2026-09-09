package com.cpt.payments_processing_service.dao;

import com.cpt.payments_processing_service.entity.PaymentTypeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentTypeEntity, Integer> {
  Optional<PaymentTypeEntity> findByTypeIgnoreCase(String type);
}
