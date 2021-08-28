package com.njtech.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njtech.mmall.entity.User;
import com.njtech.mmall.service.CartService;
import com.njtech.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    /**
     * 登录
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("login_name", loginName);
        queryWrapper.eq("password", password);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return "login";
        }else {
            session.setAttribute("user", user);
            return "redirect:/productCategory/list";
        }
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @RequestMapping("/register")
    public String register(User user, Model model){
        boolean res = false;
        try {
            res = userService.save(user);
        } catch (Exception e) {
            model.addAttribute("errMessage", user.getLoginName() + "已存在，请重新输入");
        }
        if(res) return "login";
        return "register";
    }

    @RequestMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userInfo");
        User user = (User)session.getAttribute("user");
        mv.addObject("cartVOList", cartService.getCartVO(user.getId()));
        return mv;
    }

}

