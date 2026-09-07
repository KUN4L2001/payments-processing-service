package com.cpt.payments_processing_service.service.factory;

import com.cpt.payments_processing_service.constant.TransactionStatusEnum;
import com.cpt.payments_processing_service.service.impl.handler.CreatedStatusHandler;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactoryPattern {
    @Autowired private ApplicationContext context;

    public PaymentStatusHandler getStatusHandler(TransactionStatusEnum status){
        switch (status) {
            case CREATED: {
                return context.getBean(CreatedStatusHandler.class);
            }
            default: {
                return null;
            }
        }
    }
}
