package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.dto.event.MailEvent;
import com.cpt.payments_processing_service.service.interfaces.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
  private static final String MAIL_TOPIC = "mail-events";
  private final KafkaTemplate<String, MailEvent> kafkaTemplate;

  @Override
  public void sendMailEvent(MailEvent mailEvent) {
    kafkaTemplate.send(MAIL_TOPIC, mailEvent.getTransactionId(), mailEvent);
  }
}
