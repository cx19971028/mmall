package com.njtech.mmall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Test
    void test(){
        productCategoryMapper.selectList(null).forEach(System.out::println);
    }
}