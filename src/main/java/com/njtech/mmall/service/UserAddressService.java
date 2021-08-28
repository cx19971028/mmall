package com.njtech.mmall.service;

import com.njtech.mmall.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
public interface UserAddressService extends IService<UserAddress> {

    List<UserAddress> getListByUserId(Integer id);
}
