package com.cpt.payments_processing_service.dto.request;

import jakarta.validation.Valid;
import java.util.List;
import lombok.Data;

@Data
public class InitiateRequestDTO {
  private String txnRef;
  @Valid private List<LineItemDTO> lineItems;
  private String successUrl;
  private String cancelUrl;
}
