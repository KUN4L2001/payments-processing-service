package com.cpt.payments_processing_service.constant;

import lombok.Getter;

public enum TransactionStatusEnum {
    CREATED(1,"CREATED"),
    INITIATED(2,"INITIATED"),
    SUCCESS(3,"SUCCESS"),
    PENDING(4,"PENDING"),
    FAILED(5,"FAILED"),
    NA(6,"NA");

    @Getter
    private final int id;
    @Getter
    private final String name;

    TransactionStatusEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static TransactionStatusEnum getById(int id){
        for(TransactionStatusEnum status: values()){
            if(status.getId() == id){
                return status;
            }
        }
        return NA;
    }

    public static TransactionStatusEnum getByName(String name){
        for(TransactionStatusEnum status: values()){
            if(status.getName().equalsIgnoreCase(name)){
                return status;
            }
        }
        return NA;
    }
}
