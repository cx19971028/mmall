package com.njtech.mmall.service;

import com.njtech.mmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.njtech.mmall.entity.User;
import com.njtech.mmall.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenxin
 * @since 2021-08-01
 */
public interface CartService extends IService<Cart> {

    List<CartVO> getCartVO(Integer id);
    public int removeCartById(Integer id);

}
