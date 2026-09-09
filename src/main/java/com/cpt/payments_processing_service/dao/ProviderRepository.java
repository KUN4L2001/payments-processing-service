package com.cpt.payments_processing_service.dao;

import com.cpt.payments_processing_service.entity.ProviderEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<ProviderEntity, Integer> {
  Optional<ProviderEntity> findByProviderNameIgnoreCase(String providerName);
}
