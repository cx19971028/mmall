package com.njtech.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njtech.mmall.entity.UserAddress;
import com.njtech.mmall.mapper.UserAddressMapper;
import com.njtech.mmall.mapper.UserMapper;
import com.njtech.mmall.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    public List<UserAddress> getListByUserId(Integer userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        List<UserAddress> userAddressList = userAddressMapper.selectList(wrapper);
        return userAddressList;
    }
}
