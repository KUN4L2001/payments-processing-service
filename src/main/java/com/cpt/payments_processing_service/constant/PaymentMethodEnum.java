package com.cpt.payments_processing_service.constant;

import lombok.Getter;

public enum PaymentMethodEnum {
  APM(1, "APM");
  @Getter private final int id;
  @Getter private final String name;

  PaymentMethodEnum(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public static PaymentMethodEnum getById(int id) {
    for (PaymentMethodEnum paymentMethod : values()) {
      if (paymentMethod.id == id) {
        return paymentMethod;
      }
    }
    throw new IllegalArgumentException("Invalid payment type id: " + id);
  }

  public static PaymentMethodEnum getByName(String name) {
    for (PaymentMethodEnum paymentMethod : values()) {
      if (paymentMethod.name.equalsIgnoreCase(name)) {
        return paymentMethod;
      }
    }
    throw new IllegalArgumentException("Invalid payment type name: " + name);
  }
}
