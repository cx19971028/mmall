package com.njtech.mmall.enums;

import lombok.Getter;

@Getter
public enum MyEnums {
    STOCK_ERROR(1,"库存不足");

    private Integer code;
    private String message;

    MyEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
