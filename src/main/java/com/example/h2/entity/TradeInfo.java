package com.example.h2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Trade_info")
public class TradeInfo {
    @Id
    @GeneratedValue
    private Long id;

    private Integer number;

    public TradeInfo() {
    }

    public TradeInfo(Integer number) {
        this.number = number;
    }
}
