package com.cpt.payments_processing_service.service.interfaces;

import com.cpt.payments_processing_service.dto.event.MailEvent;

public interface KafkaProducerService {
  void sendMailEvent(MailEvent mailEvent);
}
