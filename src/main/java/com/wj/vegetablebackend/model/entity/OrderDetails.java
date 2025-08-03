package com.wj.vegetablebackend.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单详情 实体类。
 *
 * @author wj
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("order_details")
public class OrderDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 订单编号
     */
    @Column("orderId")
    private Long orderId;

    /**
     * 菜品Id
     */
    @Column("dishesId")
    private Long dishesId;

    /**
     * 价格（当时价格）
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 用户id
     */
    @Column("userId")
    private Long userId;

    /**
     * 编辑时间
     */
    @Column("editTime")
    private LocalDateTime editTime;

    /**
     * 创建时间
     */
    @Column("createTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column("updateTime")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Column(value = "isDelete", isLogicDelete = true)
    private Integer isDelete;

}
