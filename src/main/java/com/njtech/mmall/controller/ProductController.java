package com.njtech.mmall.controller;


import com.njtech.mmall.entity.Product;
import com.njtech.mmall.entity.User;
import com.njtech.mmall.service.CartService;
import com.njtech.mmall.service.ProductCategoryService;
import com.njtech.mmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    /**
     * 展示二级分类的列表
     * @param type
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/list/{type}/{id}")
    public ModelAndView productList(
            @PathVariable("type") String type,
            @PathVariable("id")Integer id,
            HttpSession session){
        List<Product> productList = productService.getProductList(type, id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList",productList);
        modelAndView.addObject("list", productCategoryService.getProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            // 若用户未登录，则展示空购物车列表
            modelAndView.addObject("cartVOList", new ArrayList<>());
        }else {
            modelAndView.addObject("cartVOList", cartService.getCartVO(user.getId()));
        }
        return modelAndView;
    }

    /**
     * 进入商品详情页
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/detail/{id}")
    public ModelAndView productDetail(@PathVariable("id") Integer id, HttpSession session){
        ModelAndView mv = new ModelAndView();
        Product product = productService.getById(id);
        mv.setViewName("productDetail");
        mv.addObject("product", product);
        User user = (User)session.getAttribute("user");
        if(user == null){
            // 若用户未登录，则展示空购物车列表
            mv.addObject("cartVOList", new ArrayList<>());
        }else {
            mv.addObject("cartVOList", cartService.getCartVO(user.getId()));
        }
        mv.addObject("list", productCategoryService.getProductCategoryVO());
        return mv;
    }
}

