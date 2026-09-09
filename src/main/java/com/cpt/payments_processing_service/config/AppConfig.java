package com.cpt.payments_processing_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }

  @Bean
  public ObjectMapper objectMapper(){
      return new ObjectMapper();
  }

  @Bean
  public WebClient webClient(){
      return WebClient.builder().build();
  }
}
