package com.wj.vegetablebackend.model.dto.orders;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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
public class OrdersQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;


    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 用户id
     */
    private Long userId;
}
