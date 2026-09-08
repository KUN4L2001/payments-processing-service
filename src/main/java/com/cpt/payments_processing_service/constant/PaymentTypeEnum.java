package com.cpt.payments_processing_service.constant;

import lombok.Getter;

public enum PaymentTypeEnum {
  SALE(1, "SALE");
  @Getter private final int id;
  @Getter private final String name;

  PaymentTypeEnum(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public static PaymentTypeEnum getById(int id) {
    for (PaymentTypeEnum paymentType : values()) {
      if (paymentType.id == id) {
        return paymentType;
      }
    }
    throw new IllegalArgumentException("Invalid payment type id: " + id);
  }

  public static PaymentTypeEnum getByName(String name) {
    for (PaymentTypeEnum paymentType : values()) {
      if (paymentType.name.equalsIgnoreCase(name)) {
        return paymentType;
      }
    }
    throw new IllegalArgumentException("Invalid payment type name: " + name);
  }
}
