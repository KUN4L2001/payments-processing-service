package com.cpt.payments_processing_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "payment.query")
public class QueryProperties {
  private String createTransaction;
  private String createTransactionLog;
}
