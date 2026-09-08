package com.cpt.payments_processing_service.constant;

import lombok.Getter;

public enum ProviderEnum {
  STRIPE(1, "STRIPE");

  @Getter private final int id;
  @Getter private final String name;

  ProviderEnum(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public static ProviderEnum getById(int id) {
    for (ProviderEnum provider : values()) {
      if (provider.id == id) {
        return provider;
      }
    }
    throw new IllegalArgumentException("Invalid provider id: " + id);
  }

  public static ProviderEnum getByName(String name) {
    for (ProviderEnum provider : values()) {
      if (provider.name.equalsIgnoreCase(name)) {
        return provider;
      }
    }
    throw new IllegalArgumentException("Invalid provider name: " + name);
  }
}
