package com.njtech.mmall.service;

import com.njtech.mmall.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        User user = new User();
        user.setLoginName("123");
//        user.setGender(1);
        user.setUserName("zs");
        user.setIdentityCode("123123");
        user.setPassword("123");
        System.out.println(userService.save(user));
    }

    @Test
    public void test2(){
        User user = userService.getById(27);
        user.setUserName("zhangsan");
        System.out.println(userService.updateById(user));
    }
}