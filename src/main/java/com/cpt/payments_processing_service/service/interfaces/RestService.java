package com.cpt.payments_processing_service.service.interfaces;

import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface RestService {
  ResponseEntity<String> postRequest(
      String requestUrl, String requestBody, Map<String, String> headers);
}
