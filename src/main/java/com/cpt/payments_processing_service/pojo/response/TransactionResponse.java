package com.cpt.payments_processing_service.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private String status;
}
