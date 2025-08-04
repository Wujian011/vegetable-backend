package com.wj.vegetablebackend.model.dto.dishes;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜品修改
 */
@Data
public class DishesUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 菜谱分类id
     */
    private Long classificationId;

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
     * 菜品图片
     */
    private String dishesImage;

    /**
     * 用户id
     */
    private Long userId;
}
