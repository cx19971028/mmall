package com.njtech.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njtech.mmall.entity.ProductCategory;
import com.njtech.mmall.mapper.ProductCategoryMapper;
import com.njtech.mmall.mapper.ProductMapper;
import com.njtech.mmall.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njtech.mmall.vo.ProductCategoryVO;
import com.njtech.mmall.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductCategoryVO> getProductCategoryVO() {
        // 一级分类
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type","1");
        List<ProductCategory> levelOne = productCategoryMapper.selectList(wrapper);
        List<ProductCategoryVO> levelOneVO = new ArrayList<>();
        int i = 0;
        for (ProductCategory productCategory : levelOne) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            BeanUtils.copyProperties(productCategory, productCategoryVO);
            productCategoryVO.setBannerImg("banner"+i+".png");
            productCategoryVO.setTopImg("top"+i+".png");
            i++;
            levelOneVO.add(productCategoryVO);
            // 根据levelOneVOId 查询一级商品
            wrapper = new QueryWrapper();
            wrapper.eq("categorylevelone_id",productCategoryVO.getId());
            List<ProductVO> productVOList = productMapper.selectList(wrapper);
            productCategoryVO.setProductVOList(productVOList);
        }
        for (ProductCategoryVO levelOneProductCategoryVO : levelOneVO) {
            wrapper = new QueryWrapper();
            wrapper.eq("type","2");
            wrapper.eq("parent_id",levelOneProductCategoryVO.getId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
            List<ProductCategoryVO> levelTwoVO = new ArrayList<>();
            // 将DTO数据转成VO数据
            for (ProductCategory productCategory : levelTwo) {
                ProductCategoryVO productCategoryVO = new ProductCategoryVO();
                BeanUtils.copyProperties(productCategory, productCategoryVO);
                levelTwoVO.add(productCategoryVO);
            }
            levelOneProductCategoryVO.setChild(levelTwoVO);

            // 遍历levelTwoVO获取其子分类数据
            for (ProductCategoryVO levelTwoProductCategoryVO : levelTwoVO) {
                wrapper = new QueryWrapper();
                wrapper.eq("type","3");
                wrapper.eq("parent_id",levelTwoProductCategoryVO.getId());
                List<ProductCategory> levelThree = productCategoryMapper.selectList(wrapper);
                List<ProductCategoryVO> levelThreeVO = new ArrayList<>();
                // 将DTO数据转成VO数据
                for (ProductCategory productCategory : levelThree) {
                    ProductCategoryVO productCategoryVO = new ProductCategoryVO();
                    BeanUtils.copyProperties(productCategory, productCategoryVO);
                    levelThreeVO.add(productCategoryVO);
                }
                levelTwoProductCategoryVO.setChild(levelThreeVO);
            }
        }
        return levelOneVO;
    }
}
