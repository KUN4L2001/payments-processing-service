package com.cpt.payments_processing_service.pojo.response;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class GenericRequest<T> {
  @Valid private T data;
}
