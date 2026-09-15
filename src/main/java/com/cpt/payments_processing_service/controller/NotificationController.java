package com.cpt.payments_processing_service.controller;

import com.cpt.payments_processing_service.service.interfaces.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/payments")
public class NotificationController {
  @Autowired private NotificationService notificationService;

  @PostMapping("/stripe/notification")
  public ResponseEntity<String> notifyPayment(
      @RequestBody String payload, @RequestHeader("Stripe-Signature") String signature) {
    log.info("Notification endpoint invoked");
    log.info("Stripe webhook payload: {}", payload);
    log.info("Stripe signature: {}", signature);
    notificationService.processStripeNotification(payload, signature);
    return ResponseEntity.ok("Ok");
  }
}
