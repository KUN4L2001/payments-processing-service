package com.cpt.payments_processing_service.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "Transaction")
@Data
public class TransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "TXN_ID")
  private String txnId;

  @Column(name = "USER_ID", nullable = false)
  private Integer userId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "PAYMENT_METHOD_ID", nullable = false)
  private PaymentMethodEntity paymentMethod;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "PROVIDER_ID", nullable = false)
  private ProviderEntity provider;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "PAYMENT_TYPE_ID", nullable = false)
  private PaymentTypeEntity paymentType;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "TXN_STATUS_ID", nullable = false)
  private TransactionStatusEntity txnStatus;

  @Column(name = "AMOUNT", precision = 19, scale = 2)
  private BigDecimal amount;

  @Column(name = "CURRENCY", nullable = false, length = 3)
  private String currency;

  @Column(name = "MERC_TXN_REF", nullable = false, length = 50)
  private String merchantTransactionReference;

  @Column(name = "TXN_REF", nullable = false, unique = true, length = 50)
  private String txnReference;

  @Column(name = "PROVIDER_REF", length = 100)
  private String providerReference;

  @Column(name = "PROVIDER_CODE", length = 500)
  private String providerCode;

  @Column(name = "PROVIDER_MESSAGE", length = 1000)
  private String providerMessage;

  @Column(name = "CREATION_DATE", insertable = false, updatable = false)
  private LocalDateTime creationDate;

  @Column(name = "UPDATED_DATE", insertable = false, updatable = false)
  private LocalDateTime updatedDate;

  @Column(name = "RETRY_COUNT")
  private Integer retryCount;
}
