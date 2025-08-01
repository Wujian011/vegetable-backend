package com.wj.vegetablebackend.model.dto.dishes;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜品创建请求
 */
@Data
public class DishesAddRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;



    /**
     * 菜品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 材料
     */
    private String material;

    /**
     * 用户id
     */
    private Long userId;
}
