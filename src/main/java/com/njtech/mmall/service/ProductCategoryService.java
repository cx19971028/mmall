package com.njtech.mmall.service;

import com.njtech.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.njtech.mmall.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<ProductCategoryVO> getProductCategoryVO();
}
