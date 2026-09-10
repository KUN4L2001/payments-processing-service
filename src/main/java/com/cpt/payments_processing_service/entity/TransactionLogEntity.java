package com.cpt.payments_processing_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "Transaction_Log")
@Data
public class TransactionLogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "ID")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "TXN_ID", nullable = false)
  private TransactionEntity transaction;

  @Column(name = "TXN_FROM_STATUS", length = 50)
  private String txnFromStatus;

  @Column(name = "TXN_TO_STATUS", length = 50)
  private String txnToStatus;

  @Column(name = "CREATION_DATE", insertable = false, updatable = false)
  private LocalDateTime creationDate;
}
