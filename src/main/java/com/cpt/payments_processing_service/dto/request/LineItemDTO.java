package com.cpt.payments_processing_service.dto.request;

import lombok.Data;

@Data
public class LineItemDTO {
  private int quantity;
  private String currency;
  private String productName;
  private long unitAmount;
}
