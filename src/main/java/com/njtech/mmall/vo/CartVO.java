package com.njtech.mmall.vo;

import lombok.Data;

@Data
public class CartVO {
    private Integer id;
    private String name;
    private Integer quantity;
    private Float cost;
    private String fileName;
    private Float price;
    private Integer productId;
    private Integer stock;

}
