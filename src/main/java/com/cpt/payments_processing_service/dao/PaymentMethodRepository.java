package com.cpt.payments_processing_service.dao;

import com.cpt.payments_processing_service.entity.PaymentMethodEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Integer> {
  Optional<PaymentMethodEntity> findByNameIgnoreCase(String name);
}
