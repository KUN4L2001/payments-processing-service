package com.cpt.payments_processing_service.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailEvent {
  private String transactionId;
  private String email;
  private String subject;
  private String message;
  private String link;
}
