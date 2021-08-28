package com.njtech.mmall.controller;


import com.njtech.mmall.entity.User;
import com.njtech.mmall.service.CartService;
import com.njtech.mmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    /**
     * 商品分类
     * @param session
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        mv.addObject("list", productCategoryService.getProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            // 若用户未登录，则展示空购物车列表
            mv.addObject("cartVOList", new ArrayList<>());
        }else {
            mv.addObject("cartVOList", cartService.getCartVO(user.getId()));
        }
        return mv;
    }
}

