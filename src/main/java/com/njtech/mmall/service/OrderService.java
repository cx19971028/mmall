package com.njtech.mmall.service;

import com.njtech.mmall.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.njtech.mmall.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
public interface OrderService extends IService<Orders> {

    Orders confirmOrder(String selectAddress, String address, String remark, Float cost, User user);
}
