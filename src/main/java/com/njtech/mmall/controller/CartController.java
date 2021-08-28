package com.njtech.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njtech.mmall.entity.Cart;
import com.njtech.mmall.entity.User;
import com.njtech.mmall.entity.UserAddress;
import com.njtech.mmall.service.CartService;
import com.njtech.mmall.service.UserAddressService;
import com.njtech.mmall.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserAddressService userAddressService;

    /**
     * 加入购物车，保存购物车数据
     * @param productId
     * @param price
     * @param quantity
     * @param session
     * @return
     */
    @RequestMapping("/cartList/{productId}/{price}/{quantity}")
    public String cartList(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
            ){
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setCost(price*quantity);
        cart.setQuantity(quantity);
        cart.setUserId(((User)session.getAttribute("user")).getId());
        try {
            if(cartService.save(cart)){
               return "redirect:/cart/carDetail";
            }
        } catch (Exception e) {
            // 若有异常 返回当前页面
            return "redirect:/product/detail/"+productId;
        }
        return null;
    }

    /**
     * 查看购物车,展示购物车信息
     * @return
     */
    @RequestMapping("/carDetail")
    public ModelAndView cartDetail(HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("settlement1");
        User user = (User)session.getAttribute("user");
        mv.addObject("cartVOList", cartService.getCartVO(user.getId()));
        return mv;
    }

    @RequestMapping("/removeCart/{id}")
    public String removeCart(@PathVariable("id") Integer id){
        cartService.removeCartById(id);
        return "redirect:/cart/carDetail";
    }

    @RequestMapping("/updateCart/{id}/{quantity}/{cost}")
    @ResponseBody
    public Map<String,Object> updateCart(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity,
            @PathVariable("cost") Float cost
            ){
        Map<String, Object> result = new HashMap<>();
        Cart cart = cartService.getById(id);
        cart.setQuantity(quantity);
        cart.setCost(cost);
        if(cartService.updateById(cart)){
            result.put("success", true);
        }else {
            result.put("success", false);
        }

        return result;
    }


    /**
     * 用户结算页面
     * @param session
     * @return
     */
    @RequestMapping("/confirmCost")
    public ModelAndView confirmCost(HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("settlement2");
        User user = (User)session.getAttribute("user");
        List<UserAddress> userAddressList = userAddressService.getListByUserId(user.getId());
        mv.addObject("userAddressList",userAddressList);
        mv.addObject("cartVOList", cartService.getCartVO(user.getId()));
        return mv;
    }
}

