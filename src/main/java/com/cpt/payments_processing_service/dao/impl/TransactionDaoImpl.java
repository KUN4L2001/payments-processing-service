package com.cpt.payments_processing_service.dao.impl;

import com.cpt.payments_processing_service.constant.TransactionStatusEnum;
import com.cpt.payments_processing_service.dao.interfaces.TransactionDao;
import com.cpt.payments_processing_service.entity.PaymentRequestEntity;
import com.cpt.payments_processing_service.pojo.response.TransactionResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TransactionResponse createTransaction(PaymentRequestEntity request) {

        Query query = entityManager.createNativeQuery("""
                INSERT INTO payment_transaction
                (user_id, payment_mode, provider, currency, amount)
                VALUES
                (:userId, :paymentMode, :provider, :currency, :amount)
                """);

        query.setParameter("userId", request.getUserId());
        query.setParameter("paymentMode", request.getPaymentMode());
        query.setParameter("provider", request.getProvider());
        query.setParameter("currency", request.getCurrency());
        query.setParameter("amount", request.getAmount());

        query.executeUpdate();

        return new TransactionResponse(TransactionStatusEnum.CREATED.getName());
    }
}
