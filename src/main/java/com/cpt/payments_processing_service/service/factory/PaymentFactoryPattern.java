package com.cpt.payments_processing_service.service.factory;

import com.cpt.payments_processing_service.service.impl.handler.CreatedStatusHandler;
import com.cpt.payments_processing_service.service.interfaces.PaymentStatusHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactoryPattern {
    @Autowired private ApplicationContext context;

    public PaymentStatusHandler getStatusHandler(String mode){
        switch (mode) {
            case "CREATED": {
                CreatedStatusHandler createdStatusHandler = context.getBean(CreatedStatusHandler.class);
                return createdStatusHandler;
            }
            default: {
                return null;
            }
        }
    }
}
