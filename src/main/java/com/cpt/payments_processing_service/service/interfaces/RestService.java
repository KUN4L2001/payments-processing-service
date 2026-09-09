package com.cpt.payments_processing_service.service.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RestService {
    ResponseEntity<String> postRequest(String requestUrl, String requestBody, Map<String, String> headers);
}
