package com.njtech.mmall.service.impl;

import com.njtech.mmall.entity.User;
import com.njtech.mmall.mapper.UserMapper;
import com.njtech.mmall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
