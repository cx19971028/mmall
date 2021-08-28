package com.njtech.mmall.controller;


import com.njtech.mmall.entity.Orders;
import com.njtech.mmall.entity.User;
import com.njtech.mmall.service.CartService;
import com.njtech.mmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/confirmOrder")
    public ModelAndView confirmOrder(
            String selectAddress,
            String address,
            String remark,
            Float cost,
            HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("settlement3");
        User user = (User)session.getAttribute("user");
        Orders orders = orderService.confirmOrder(selectAddress,address,remark,cost, user);
        mv.addObject("cartVOList", cartService.getCartVO(user.getId()));
        mv.addObject("orders", orders);
        if(selectAddress.equals("newAddress")){
            mv.addObject("address", address);
        }else {
            mv.addObject("address",selectAddress);
        }
        return mv;
    }

}

