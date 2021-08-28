package com.njtech.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njtech.mmall.entity.*;
import com.njtech.mmall.mapper.CartMapper;
import com.njtech.mmall.mapper.OrderDetailMapper;
import com.njtech.mmall.mapper.OrderMapper;
import com.njtech.mmall.mapper.UserAddressMapper;
import com.njtech.mmall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Orders confirmOrder(
            String selectAddress,
            String address,
            String remark,
            Float cost,
            User user) {
        // 若为新地址则插入新数据
        if(selectAddress.equals("newAddress")){
            UserAddress userAddress = new UserAddress();
            userAddress.setUserId(user.getId());
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setIsdefault(1);

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id", user.getId());
            wrapper.eq("isdefault", 1);

            UserAddress userAddress1 = userAddressMapper.selectOne(wrapper);
            userAddress1.setIsdefault(0);
            userAddressMapper.updateById(userAddress1);
            userAddressMapper.insert(userAddress);
        }else {
            address = selectAddress;
        }
        // 插入订单记录
        Orders orders = new Orders();
        orders.setUserId(user.getId());
        orders.setCost(cost);
        orders.setLoginName(user.getLoginName());
        orders.setUserAddress(address);
        orders.setSerialnumber(address+"123456890");
        orderMapper.insert(orders);

        // 根据UserId查购物车
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orders.getId());
            orderDetail.setProductId(cart.getProductId());
            orderDetail.setCost(cart.getCost());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetailMapper.insert(orderDetail);
        }

        // 清空购物车
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("user_id", user.getId());
        cartMapper.delete(wrapper1);

        return orders;
    }
}
