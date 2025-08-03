package com.wj.vegetablebackend.model.dto.orders;


import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜品修改
 */
@Data
public class OrdersUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;


    /**
     * 价格
     */
    private BigDecimal price;
}
