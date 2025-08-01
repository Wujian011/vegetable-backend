package com.wj.vegetablebackend.model.dto.dishes;

import com.wj.vegetablebackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜品查询参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DishesQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

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
