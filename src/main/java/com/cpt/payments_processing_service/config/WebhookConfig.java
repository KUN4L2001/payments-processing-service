package com.cpt.payments_processing_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "webhook-secret")
public class WebhookConfig {
  private String stripeKey;
}
