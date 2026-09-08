package com.cpt.payments_processing_service.dto.request;

import lombok.Data;

@Data
public class PaymentRequestDTO {
	private String userId;
	private String paymentMethod;
	private String provider;
	private String paymentType;
	private String txnStatus;
	private String currency;
	private String amount;
	private String merchantTxnRef;
	private String txnReference;
}