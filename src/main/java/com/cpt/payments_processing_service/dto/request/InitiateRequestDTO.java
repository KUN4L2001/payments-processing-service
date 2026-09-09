package com.cpt.payments_processing_service.dto.request;

import java.util.List;

public class InitiateRequestDTO {
    private String txnRef;
    private List<LineItemDTO> lineItems;
    private String successUrl;
    private String cancelUrl;
}
