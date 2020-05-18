package com.example.h2.enums;

public enum OperatinTypeEnum {

    INSERT("INSERT", "新增"),
    UPDATE("UPDATE", "更新"),
    CANCLE("CANCEL", "取消");

    private String code;

    private String name;

    OperatinTypeEnum(String code, String name) {
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
