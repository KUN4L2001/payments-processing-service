package com.cpt.payments_processing_service.dao.impl;

import com.cpt.payments_processing_service.config.QueryProperties;
import com.cpt.payments_processing_service.constant.PaymentMethodEnum;
import com.cpt.payments_processing_service.constant.PaymentTypeEnum;
import com.cpt.payments_processing_service.constant.ProviderEnum;
import com.cpt.payments_processing_service.constant.TransactionStatusEnum;
import com.cpt.payments_processing_service.dao.interfaces.TransactionDao;
import com.cpt.payments_processing_service.entity.PaymentRequestEntity;
import com.cpt.payments_processing_service.pojo.response.TransactionResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionDaoImpl implements TransactionDao {

  @PersistenceContext private EntityManager entityManager;
  @Autowired private QueryProperties queryProperties;

  @Override
  public TransactionResponse createTransaction(PaymentRequestEntity request) {

    log.debug("Query: {}", queryProperties.getCreateTransaction());
    Query query = entityManager.createNativeQuery(queryProperties.getCreateTransaction());

    query.setParameter("userId", request.getUserId());
    query.setParameter(
        "paymentMethodId", PaymentMethodEnum.getByName(request.getPaymentMethod()).getId());
    query.setParameter("providerId", ProviderEnum.getByName(request.getProvider()).getId());
    query.setParameter(
        "paymentTypeId", PaymentTypeEnum.getByName(request.getPaymentType()).getId());
    query.setParameter(
        "txnStatusId", TransactionStatusEnum.getByName(request.getTxnStatus()).getId());
    query.setParameter("amount", request.getAmount());
    query.setParameter("currency", request.getCurrency());
    query.setParameter("merchantTransactionReference", request.getMerchantTxnRef());
    query.setParameter("txnReference", request.getTxnReference());

    query.executeUpdate();

    return new TransactionResponse(TransactionStatusEnum.CREATED.getName());
  }
}
