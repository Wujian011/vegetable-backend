package com.wj.vegetablebackend.model.dto.orderdetails;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情添加请求
 */
@Data
public class OrdersDetailsAddRequest {


    /**
     * 菜品Id
     */
    private Long dishesId;

    /**
     * 价格（当时价格）
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer number;
}
