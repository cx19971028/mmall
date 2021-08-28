package com.njtech.mmall.service.impl;

import com.njtech.mmall.entity.Product;
import com.njtech.mmall.mapper.ProductMapper;
import com.njtech.mmall.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductList(String type, Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("categorylevel"+type+"_id", id);
        List<Product> productList = productMapper.selectByMap(map);
        return productList;
    }
}
