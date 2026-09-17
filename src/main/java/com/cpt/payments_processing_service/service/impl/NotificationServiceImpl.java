package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.config.WebhookConfig;
import com.cpt.payments_processing_service.dao.TransactionRepository;
import com.cpt.payments_processing_service.dao.TransactionStatusRepository;
import com.cpt.payments_processing_service.entity.TransactionEntity;
import com.cpt.payments_processing_service.entity.TransactionStatusEntity;
import com.cpt.payments_processing_service.service.interfaces.NotificationService;
import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationServiceImpl implements NotificationService {

  @Autowired private WebhookConfig webhookConfig;
  @Autowired private TransactionRepository transactionRepository;
  @Autowired private TransactionStatusRepository transactionStatusRepository;

  @Override
  public void processStripeNotification(String payload, String signature) {
    try {
      // verification of payload. Payload string will be visible to all but any one
      // between changes anything then the signature will not match with
      // modified body and give error
      Event event = Webhook.constructEvent(payload, signature, webhookConfig.getStripeKey());
      log.info("Stripe event: {}", event);
      if ("checkout.session.completed".equals(event.getType())) {
        Session session = (Session) event.getDataObjectDeserializer().deserializeUnsafe();

        String sessionId = session.getId();

        log.info("Stripe Checkout Session ID: {}", sessionId);

        TransactionEntity transaction = transactionRepository.getByProviderReference(sessionId);

        TransactionStatusEntity transactionStatusEntity =
            transactionStatusRepository
                .findByNameIgnoreCase("SUCCESS")
                .orElseThrow(
                    () -> new IllegalArgumentException("Transaction status not found: SUCCESS"));
        transaction.setTxnStatus(transactionStatusEntity);

        transactionRepository.save(transaction);
        log.info("Transaction fetched and saved as success: {}", transaction);
      }
    } catch (SignatureVerificationException e) {
      // Data tampering exception
      throw new RuntimeException(e);
    } catch (EventDataObjectDeserializationException e) {
      throw new RuntimeException(e);
    }
  }
}
