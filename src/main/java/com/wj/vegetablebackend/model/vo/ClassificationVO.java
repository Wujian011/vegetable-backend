package com.wj.vegetablebackend.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜品分类
 */
@Data
public class ClassificationVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类代码
     */
    private String code;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
