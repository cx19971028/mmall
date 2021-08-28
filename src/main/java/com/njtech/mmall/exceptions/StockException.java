package com.njtech.mmall.exceptions;

import com.njtech.mmall.enums.MyEnums;

public class StockException extends RuntimeException{

    public StockException(String message) {
        super(message);
    }

    public StockException(MyEnums myEnums) {
        super(myEnums.getMessage());
    }
}
