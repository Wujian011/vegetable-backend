package com.wj.vegetablebackend.model.dto.orders;

import com.wj.vegetablebackend.model.dto.orderdetails.OrdersDetailsAddRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdersAddRequest {


    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 订单详情
     */
    List<OrdersDetailsAddRequest> ordersDetailsAddRequestList;
}
