package com.wj.vegetablebackend.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.wj.vegetablebackend.model.dto.orders.OrdersAddRequest;
import com.wj.vegetablebackend.model.dto.orders.OrdersQueryRequest;
import com.wj.vegetablebackend.model.entity.Orders;
import com.wj.vegetablebackend.model.entity.Orders;
import com.wj.vegetablebackend.model.entity.User;
import com.wj.vegetablebackend.model.vo.OrdersVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 订单 服务层。
 *
 * @author wj
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 根据查询条件构造数据查询参数
     *
     * @param ordersQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(OrdersQueryRequest ordersQueryRequest);


    /**
     * 获取脱敏后的订单信息
     *
     * @param orders 订单信息
     * @return
     */
    OrdersVO getOrdersVO(Orders orders);

    /**
     * 获取脱敏后的订单信息（分页）
     *
     * @param ordersList 订单列表
     * @return
     */
    List<OrdersVO> getOrdersVOList(List<Orders> ordersList);

    /**
     * 添加订单
     * @param ordersAddRequest
     * @param loginUser
     * @return
     */
    Long addOrders(OrdersAddRequest ordersAddRequest,  User loginUser);
}
