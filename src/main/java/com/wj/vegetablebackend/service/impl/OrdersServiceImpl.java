package com.wj.vegetablebackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.sql.Order;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.wj.vegetablebackend.exception.BusinessException;
import com.wj.vegetablebackend.exception.ErrorCode;
import com.wj.vegetablebackend.model.dto.orderdetails.OrdersDetailsAddRequest;
import com.wj.vegetablebackend.model.dto.orders.OrdersAddRequest;
import com.wj.vegetablebackend.model.dto.orders.OrdersQueryRequest;
import com.wj.vegetablebackend.model.entity.OrderDetails;
import com.wj.vegetablebackend.model.entity.Orders;
import com.wj.vegetablebackend.mapper.OrdersMapper;
import com.wj.vegetablebackend.model.entity.User;
import com.wj.vegetablebackend.model.vo.DishesVO;
import com.wj.vegetablebackend.model.vo.OrderDetailsVO;
import com.wj.vegetablebackend.model.vo.OrdersVO;
import com.wj.vegetablebackend.service.DishesService;
import com.wj.vegetablebackend.service.OrderDetailsService;
import com.wj.vegetablebackend.service.OrdersService;
import com.wj.vegetablebackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单 服务层实现。
 *
 * @author wj
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Resource
    private OrderDetailsService orderDetailsService;

    @Resource
    private PlatformTransactionManager transactionManager;

    @Resource
    private DishesService dishesService;

    @Override
    public QueryWrapper getQueryWrapper(OrdersQueryRequest ordersQueryRequest) {


        if (ordersQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = ordersQueryRequest.getId();
        BigDecimal price = ordersQueryRequest.getPrice();
        Long userId = ordersQueryRequest.getUserId();
        String sortField = ordersQueryRequest.getSortField();
        String sortOrder = ordersQueryRequest.getSortOrder();

        return QueryWrapper.create()
                .eq("id", id) // where id = ${id}
                .eq("price", price)
                .eq("userId", userId)
                .orderBy(sortField, "ascend".equals(sortOrder));
    }

    @Override
    public OrdersVO getOrdersVO(Orders orders) {
        if (orders == null) {
            return null;
        }
        OrdersVO ordersVO = new OrdersVO();
        BeanUtil.copyProperties(orders, ordersVO);
        // 查询订单详情
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("orderId", orders.getId());
        List<OrderDetails> orderDetailsList = orderDetailsService.list(queryWrapper);
        List<Long> dishesIdList = orderDetailsList.stream()
                .map(OrderDetails::getDishesId).toList();
        Map<Long, DishesVO> dishesVOMap = dishesService.listByIds(dishesIdList).stream().map(dishes -> {
            DishesVO dishesVO = new DishesVO();
            BeanUtil.copyProperties(dishes, dishesVO);
            return dishesVO;
        }).collect(Collectors.toMap(DishesVO::getId, dishesVO -> dishesVO));

        List<OrderDetailsVO> orderDetailsVOList = orderDetailsList.stream().map(orderDetails -> {
            OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
            BeanUtil.copyProperties(orderDetails, orderDetailsVO);
            Long dishesId = orderDetails.getDishesId();
            orderDetailsVO.setDishesVO(dishesVOMap.get(dishesId));
            return orderDetailsVO;
        }).toList();
        ordersVO.setOrderDetailsVOList(orderDetailsVOList);

        return ordersVO;
    }

    @Override
    public List<OrdersVO> getOrdersVOList(List<Orders> ordersList) {
        if (CollUtil.isEmpty(ordersList)) {
            return new ArrayList<>();
        }
        return ordersList.stream()
                .map(this::getOrdersVO)
                .collect(Collectors.toList());
    }

    @Override
    public Long addOrders(OrdersAddRequest ordersAddRequest, User loginUser) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Orders orders = new Orders();
            BeanUtil.copyProperties(ordersAddRequest, orders);
            Long userId = loginUser.getId();
            orders.setUserId(userId);
            //订单编号
            String orderNumber = RandomUtil.randomNumbers(10);
            orders.setOrderNumber(orderNumber);
            boolean save = this.save(orders);
            if (!save) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "添加订单失败");
            }
            // 订单详情
            List<OrdersDetailsAddRequest> ordersDetailsAddRequestList = ordersAddRequest.getOrdersDetailsAddRequestList();
            List<OrderDetails> orderDetailsList = ordersDetailsAddRequestList.stream().map(ordersDetailsAddRequest -> {
                OrderDetails orderDetails = new OrderDetails();
                BeanUtil.copyProperties(ordersDetailsAddRequest, orderDetails);
                orderDetails.setOrderId(orders.getId());
                orderDetails.setUserId(userId);
                return orderDetails;
            }).toList();
            boolean saveBatch = orderDetailsService.saveBatch(orderDetailsList);
            if (!saveBatch) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "添加订单详情失败");
            }
            transactionManager.commit(status);
            return orders.getId();

        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("添加订单失败", e);
            return -1L;
        }

    }
}
