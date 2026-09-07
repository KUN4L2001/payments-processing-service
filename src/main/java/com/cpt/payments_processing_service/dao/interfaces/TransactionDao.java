package com.cpt.payments_processing_service.dao.interfaces;

import com.cpt.payments_processing_service.entity.PaymentRequestEntity;

public interface TransactionDao {
    String createTransaction(PaymentRequestEntity request);
}
