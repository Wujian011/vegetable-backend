package com.wj.vegetablebackend.model.dto.classification;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜品分类创建请求
 */
@Data
public class ClassificationAddRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类代码
     */
    private String code;

    /**
     * 创建用户id
     */
    private Long userId;
}
