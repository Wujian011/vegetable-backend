package com.wj.vegetablebackend.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.wj.vegetablebackend.model.entity.OrderDetails;
import com.wj.vegetablebackend.mapper.OrderDetailsMapper;
import com.wj.vegetablebackend.model.vo.OrderDetailsVO;
import com.wj.vegetablebackend.service.OrderDetailsService;
import com.wj.vegetablebackend.service.OrdersService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单详情 服务层实现。
 *
 * @author wj
 */
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails>  implements OrderDetailsService{



    @Override
    public List<OrderDetailsVO> getOrderDetailsByOrderId(long orderId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(OrderDetails::getOrderId, orderId);
        this.list();
        return List.of();
    }
}
