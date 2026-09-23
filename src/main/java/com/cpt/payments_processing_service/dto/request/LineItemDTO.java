package com.cpt.payments_processing_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LineItemDTO {
  @NotNull(message = "Quantity is required")
  @Positive(message = "Quantity must be greater than 0")
  private int quantity;

  private String currency;
  private String productName;
  private long unitAmount;
}
