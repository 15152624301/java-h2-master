package com.example.h2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue
    private Long transactionID;

    private Integer traeId;

    private Integer version;

    private String securityCode;

    private Integer quantity;

    private String operation;

    private String type;

    public Transactions() {
    }

    public Transactions(Integer traeId, Integer version, String securityCode, Integer quantity, String operation, String type) {
        this.traeId = traeId;
        this.version = version;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.operation = operation;
        this.type = type;
    }
}
