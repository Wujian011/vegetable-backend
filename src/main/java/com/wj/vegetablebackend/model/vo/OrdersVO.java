package com.wj.vegetablebackend.model.vo;

import com.wj.vegetablebackend.model.entity.OrderDetails;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdersVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 菜品Id
     */
    private Long dishesId;

    /**
     * 价格
     */
    private BigDecimal price;


    /**
     * 订单详情
     */
    private List<OrderDetailsVO> orderDetailsVOList;

}
