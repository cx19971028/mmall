package com.njtech.mmall.controller;


import com.njtech.mmall.entity.User;
import com.njtech.mmall.entity.UserAddress;
import com.njtech.mmall.service.CartService;
import com.njtech.mmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("/addressAdmin")
    public ModelAndView addressAmdin(HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userAddressList");
        User user = (User)session.getAttribute("user");
        mv.addObject("cartVOList", cartService.getCartVO(user.getId()));
        List<UserAddress> userAddressList = userAddressService.getListByUserId(user.getId());
        mv.addObject("addressList",userAddressList);
        return mv;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        boolean res = userAddressService.removeById(id);
        if(res){
            return "redirect:/userAddress/addressAdmin";
        }
        return null;
    }
}

