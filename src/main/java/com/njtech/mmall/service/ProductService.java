package com.njtech.mmall.service;

import com.njtech.mmall.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
public interface ProductService extends IService<Product> {

    List<Product> getProductList(String type, Integer id);
}
