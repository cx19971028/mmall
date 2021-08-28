package com.njtech.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njtech.mmall.entity.*;
import com.njtech.mmall.enums.MyEnums;
import com.njtech.mmall.exceptions.StockException;
import com.njtech.mmall.mapper.CartMapper;
import com.njtech.mmall.mapper.ProductMapper;
import com.njtech.mmall.mapper.UserAddressMapper;
import com.njtech.mmall.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njtech.mmall.vo.CartVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
// 打印日志
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CartMapper cartMapper;


    @Override
    public boolean save(Cart entity) {
        // 根据productId查询product的库存
        Product product = productMapper.selectById(entity.getProductId());
        Integer stock = product.getStock() - entity.getQuantity();
        // 如果库存小于本次购买的个数 则抛异常
        if (stock < 0 || entity.getQuantity()==0){
            // 打印日志
            log.info("异常原因：库存数为{}",stock);
            throw new StockException(MyEnums.STOCK_ERROR);
        }
        // 若库存充足，则修改product的库存
        product.setStock(stock);
        productMapper.updateById(product);
        // 添加数据进入购物车
        int res = cartMapper.insert(entity);
        if(res==1){
            return true;
        }
        return false;
    }

    @Override
    public List<CartVO> getCartVO(Integer id) {
        // 根据用户的id查出用户的购物车所有商品
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);
        List<Cart> cartList = cartMapper.selectList(wrapper);
        List<CartVO> cartVOList = new ArrayList<>();
        // 将购物车和product的信息存放VO中
        for (Cart cart : cartList) {
            CartVO cartVO = new CartVO();

            //根据购物车里的productId查出product的信息
            Product product = productMapper.selectById(cart.getProductId());
            BeanUtils.copyProperties(product,cartVO);
            BeanUtils.copyProperties(cart, cartVO);
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }


    public int removeCartById(Integer id){
        // 首先根据ID查出购买数量和productId
        Cart cart = cartMapper.selectById(id);
        // 根据productId查出商品库存
        Product product = productMapper.selectById(cart.getProductId());
        // 更新商品库存
        Integer stock = product.getStock() + cart.getQuantity();
        product.setStock(stock);
        if(productMapper.updateById(product)==1 &&
        // 删除购物车
        cartMapper.deleteById(id)==1
        ){
            return 1;
        }
        return 0;
    }


}
