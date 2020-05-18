package com.example.h2.enums;

public enum BuyOrSellTypeEnum {

    BUY("Buy", "买"),

    SELL("Sell", "卖");

    private String code;

    private String name;

    BuyOrSellTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
