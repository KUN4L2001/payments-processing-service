package com.cpt.payments_processing_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "Provider")
@Data
public class ProviderEntity {
  @Id
  @Column(name = "ID")
  private Integer id;

  @Column(name = "PROVIDER_NAME", nullable = false)
  private String providerName;

  @Column(name = "STATUS")
  private Integer status;

  @Column(name = "CREATION_DATE")
  private LocalDateTime creationDate;
}
