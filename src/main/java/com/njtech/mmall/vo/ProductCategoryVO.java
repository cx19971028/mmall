package com.njtech.mmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {
    private Integer id;
    private String name;
    private List<ProductCategoryVO> child;
    private String bannerImg;
    private String topImg;
    private List<ProductVO> productVOList;
}
