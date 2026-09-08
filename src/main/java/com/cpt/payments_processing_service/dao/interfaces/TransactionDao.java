package com.cpt.payments_processing_service.dao.interfaces;

import com.cpt.payments_processing_service.entity.PaymentRequestEntity;
import com.cpt.payments_processing_service.pojo.response.TransactionResponse;

public interface TransactionDao {
  TransactionResponse createTransaction(PaymentRequestEntity request);
}
