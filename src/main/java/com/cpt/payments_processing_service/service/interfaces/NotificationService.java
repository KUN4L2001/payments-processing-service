package com.cpt.payments_processing_service.service.interfaces;

public interface NotificationService {
  void processStripeNotification(String payload, String signature);
}
