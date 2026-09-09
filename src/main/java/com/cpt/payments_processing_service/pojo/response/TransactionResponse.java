package com.cpt.payments_processing_service.pojo.response;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TransactionResponse {
  @NonNull private String id;
  @NonNull private String status;
}
