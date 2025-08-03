package com.wj.vegetablebackend.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.wj.vegetablebackend.model.entity.ShoppingCart;
import com.wj.vegetablebackend.mapper.ShoppingCartMapper;
import com.wj.vegetablebackend.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * 购物车 服务层实现。
 *
 * @author wj
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>  implements ShoppingCartService{

}
