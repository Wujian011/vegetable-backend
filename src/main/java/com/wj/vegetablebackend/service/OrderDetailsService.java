package com.wj.vegetablebackend.service;

import com.mybatisflex.core.service.IService;
import com.wj.vegetablebackend.model.entity.OrderDetails;
import com.wj.vegetablebackend.model.vo.OrderDetailsVO;

import java.util.List;

/**
 * 订单详情 服务层。
 *
 * @author wj
 */
public interface OrderDetailsService extends IService<OrderDetails> {

    /**
     * 根据订单id查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetailsVO> getOrderDetailsByOrderId(long orderId);
}
