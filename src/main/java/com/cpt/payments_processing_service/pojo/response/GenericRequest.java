package com.cpt.payments_processing_service.pojo.response;

import lombok.Data;

@Data
public class GenericRequest<T> {
  private T data;
}
