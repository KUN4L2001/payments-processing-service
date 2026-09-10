package com.cpt.payments_processing_service.service.impl;

import com.cpt.payments_processing_service.service.interfaces.RestService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RestServiceWithWebClient implements RestService {

  @Autowired private WebClient webClient;

  private ResponseEntity<String> processRequest(
      String requestUrl, String requestBody, Map<String, String> headers, HttpMethod httpMethod) {
    return webClient
        .method(httpMethod)
        .uri(requestUrl)
        .headers(httpHeaders -> headers.forEach(httpHeaders::set))
        .bodyValue(requestBody)
        .retrieve()
        .toEntity(String.class)
        .block();
  }

  @Override
  public ResponseEntity<String> postRequest(
      String requestUrl, String requestBody, Map<String, String> headers) {
    return processRequest(requestUrl, requestBody, headers, HttpMethod.POST);
  }
}
