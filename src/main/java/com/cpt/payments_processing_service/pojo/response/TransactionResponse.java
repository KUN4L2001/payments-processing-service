package com.cpt.payments_processing_service.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TransactionResponse {
  @NonNull private String status;
}
