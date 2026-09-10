package com.cpt.payments_processing_service.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TransactionResponseDTO {
  @NonNull private String id;
  @NonNull private String status;
}
