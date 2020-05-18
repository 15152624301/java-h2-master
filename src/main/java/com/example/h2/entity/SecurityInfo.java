package com.example.h2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Security_info")
public class SecurityInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String securityCode;

    private Integer quantity;

    public SecurityInfo() {
    }

    public SecurityInfo(String securityCode, Integer quantity) {
        this.securityCode = securityCode;
        this.quantity = quantity;
    }
}
